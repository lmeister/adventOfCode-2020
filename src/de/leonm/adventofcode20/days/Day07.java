package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 extends Day {
  List<String> input;
  HashMap<String, List<BagInformation>> bagAndContents;

  /** Constructor for Day06. Reads the input into a string list. Makes the input rules neater. */
  public Day07() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day07.txt");
      bagAndContents = new HashMap<>();

      // Could just use regex on the whole thing I guess..
      final String regexBaginfo = "(?<amount>\\d)(?<bagAndColor>[\\w|\\s]+)";

      input.forEach(
          rule -> {
            // This leaves us with a string like
            // drab blue,3 bright coral,3 dim coral
            rule = rule.replace("bags contain", ",");
            rule = rule.replace("bags", "");
            rule = rule.replace("bag", "");
            rule = rule.replace(" , ", ",");
            rule = rule.replace(" .", "");
            // First element is the bag, afterwards the contents
            String[] contents = rule.split(",");

            List<BagInformation> list = new ArrayList<>();
            Pattern pattern = Pattern.compile(regexBaginfo);
            Matcher matcher;
            for (int i = 1; i < contents.length; i++) {
              matcher = pattern.matcher(contents[i]);
              if (matcher.find()) {
                list.add(
                    new BagInformation(
                        matcher.group("bagAndColor").trim(),
                        Integer.parseInt(matcher.group("amount"))));
              }
            }
            bagAndContents.put(contents[0], list);
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solves part one of day 7.
   *
   * @return the amount of bags that can contain a "shiny gold" bag
   */
  public int partOne() {
    int amountOfBags = 0;
    for (String bag : bagAndContents.keySet()) {
      if (containsColorBag(bag, "shiny gold")) {
        amountOfBags++;
      }
    }
    return amountOfBags;
  }

  /**
   * Solves part two of day 7.
   *
   * @return the amount of bags brought when bringing a shiny gold bag
   */
  public int partTwo() {
    // Subtract one since we have to start off with 1 for the multiplication
    return calculateAmountOfBagsInColorBag("shiny gold") - 1;
  }

  /**
   * Checks if a bag contains a bag of given color.
   * Works recursively.
   *
   * @param bag The given bag to be checked
   * @param color The color of the bag that is searched for
   * @return a boolean value on whether there can eventually be a big of given color
   */
  public boolean containsColorBag(String bag, String color) {
    for (BagInformation bagInformation : bagAndContents.get(bag)) {
      // in case we already have the desired color present
      if (bagInformation.color.equalsIgnoreCase(color)) {
        return true;
      }
      if (containsColorBag(bagInformation.color, color)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Calculates the amount of bags carried in the bag of specified color.
   * Result is one off due to setting amount = 1, however required for the multiplication.
   *
   * @param color The color of the bag that we bring.
   * @return The amount of bags - CAREFUL, subtract by one when using!!
   */
  public int calculateAmountOfBagsInColorBag(String color) {
    int amount = 1;
    for (BagInformation bagInformation : bagAndContents.get(color)) {
      amount += bagInformation.amount * calculateAmountOfBagsInColorBag(bagInformation.color);
    }
    return amount;
  }

  /** Prints the solutions for the day. */
  @Override
  public void printSolutions() {
    System.out.println("Part one: " + partOne());
    System.out.println("Part two: " + partTwo());
  }

  public static class BagInformation {
    public String color;
    public int amount;

    public BagInformation(String color, int amount) {
      this.color = color;
      this.amount = amount;
    }

    @Override
    public String toString() {
      return "BagInformation{" + "color='" + color + '\'' + ", amount=" + amount + '}';
    }
  }
}
