package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 extends Day {
  List<String> input;

  /**
   * Constructor for Day02 class.
   * Reads the input as well.
   */
  public Day02() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day02.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Solves part one of day02.
   *
   * @return the amount of valid passwords in day02
   */
  public int partOne(List<String> input) {
    int amountOfValidPasswords = 0;

    for (String current : input) {
      String[] values = takeStringApart(current);
      int lowerBoundary = Integer.parseInt(values[0]);
      int upperBoundary = Integer.parseInt(values[1]);
      char required = values[2].charAt(0);
      String password = values[3];

      if (isValidPasswordOne(lowerBoundary, upperBoundary, required, password)) {
        amountOfValidPasswords++;
      }
    }
    return amountOfValidPasswords;
  }

  /**
   * Solves part two of Day02.
   *
   * @return the amount of valid passwords for part two
   */
  public int partTwo(List<String> input) {
    int amountOfValidPasswords = 0;

    for (String current : input) {
      String[] values = takeStringApart(current);
      int firstIndex = Integer.parseInt(values[0]) - 1;
      int secondIndex = Integer.parseInt(values[1]) - 1;
      char required = values[2].charAt(0);
      String password = values[3];

      if (isValidPasswordTwo(firstIndex, secondIndex, required, password)) {
        amountOfValidPasswords++;
      }
    }
    return amountOfValidPasswords;
  }

  /**
   * Takes apart a string via regex for Day02.
   * Array has a length of 4.
   * [0] = first number, [1] = second number, [2] the char to look for, [3] the password string.
   * These values need to be parsed/casted.
   *
   * @param str the string to be split
   * @return the array filled with values as strings
   */
  public String[] takeStringApart(String str) {
    // TODO This Regex can probably be optimized
    Pattern pattern =
        Pattern.compile("(?<lowerB>\\d+)-(?<upperB>\\d+)\\s(?<char>[a-z]):\\s(?<password>[a-z]+)");
    Matcher matcher;
    String[] output = new String[4];

    matcher = pattern.matcher(str);
    if (matcher.find()) {
      output[0] = matcher.group("lowerB");
      output[1] = matcher.group("upperB");
      output[2] = matcher.group("char");
      output[3] = matcher.group("password");
    }
    return output;
  }

  /**
   * Returns whether a password is valid for part one.
   * Iterates through string password and counts occurences of char required.
   *
   * @param lowerB lower boundary
   * @param upperB upper boundary
   * @param required required char
   * @param password Password to be examined
   * @return Evaluates to true if occurences of char in string is >= lowerB and <= upperB
   */
  public boolean isValidPasswordOne(int lowerB, int upperB, char required, String password) {
    int count = 0;
    for (int i = 0; i < password.length(); i++) {
      if (password.charAt(i) == required) {
        count++;
      }
    }
    return count >= lowerB && count <= upperB;
  }

  /**
   * Returns whether a password is valid for part two.
   * Must match exactly the character in exactly one place.
   *
   * @param firstIndex first position
   * @param secondIndex second position
   * @param required char that is required
   * @param password the password to examine
   * @return boolean value, true is a valid password
   */
  public boolean isValidPasswordTwo(
      int firstIndex, int secondIndex, char required, String password) {
    return password.charAt(firstIndex) == required ^ password.charAt(secondIndex) == required;
  }

  /**
   * Prints the solutions for the day.
   */
  @Override
  public void printSolutions() {
    System.out.println("Part one: " + partOne(input));
    System.out.println("Part two: " + partTwo(input));
  }
}
