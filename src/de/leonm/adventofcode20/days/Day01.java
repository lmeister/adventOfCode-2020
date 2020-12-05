package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

public class Day01 extends Day {
  List<Integer> input;

  /**
   * Constructor for Day01 Class.
   * Reads the input as well.
   */
  public Day01() {
    try {
      input = reader.getIntListFromFile("src/de/leonm/adventofcode20/input/day01.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solves part one of Day01.
   * Has a hashset which keeps the inputs Iterates through input.
   * Checks if hashset has an element x where current + x = 2020 if not, adds x to the set.
   *
   * @param input List of integers
   * @return the product of the two integers adding to 2020
   */
  public int partOne(List<Integer> input) {
    HashSet<Integer> candidates = new HashSet<>();
    int num1 = 0;
    int num2 = 0;

    for (int i : input) {
      int match = 2020 - i;
      if (candidates.contains(match)) {
        num1 = i;
        num2 = match;
        break;
      }
      candidates.add(i);
    }
    return num1 * num2;
  }

  /**
   * Solves part two of day01.
   * Works similar to part01.
   *
   * @param input A list of integers
   * @return the solution
   */
  public int partTwo(List<Integer> input) {
    HashSet<Integer> candidates = new HashSet<>();
    int num1 = 0;
    int num2 = 0;
    int num3 = 0;

    outer:
    for (int i = 0; i < input.size() - 2; i++) {
      int match = 2020 - input.get(i);
      for (int j = i + 1; j < input.size(); j++) {
        if (candidates.contains(match - input.get(j))) {
          num1 = input.get(i);
          num2 = input.get(j);
          num3 = match - input.get(j);
          break outer;
        }
        candidates.add(input.get(j));
      }
    }
    return num1 * num2 * num3;
  }

  public void printSolutions() {
    System.out.println("Part one: " + partOne(input));
    System.out.println("Part two: " + partTwo(input));
  }
}
