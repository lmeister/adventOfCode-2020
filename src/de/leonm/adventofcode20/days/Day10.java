package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day10 extends Day {
  List<Integer> input;


  /**
   * Constructor for Day10.
   *
   */
  public Day10() {
    try {
      input = reader.getIntListFromFile("src/de/leonm/adventofcode20/input/day10.txt");
      Collections.sort(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solves part one of day10.
   *
   * @return result as integer
   */
  public int partOne() {
    int differenceOfOne = 0;
    int differenceOfThree = 0;

    input.add(input.get(input.size() - 1) + 3); // for built in
    input.add(0, 0); // for the outlet

    for (int i = 1; i < input.size(); i++) {
      int diff = input.get(i) - input.get(i - 1);
      if (diff == 1) {
        differenceOfOne++;
      } else if (diff == 3) {
        differenceOfThree++;
      }
    }
    return differenceOfOne * differenceOfThree;
  }

  /**
   * Solves part two of day10.
   *
   * @return result of the computation as long
   */
  public Long partTwo() {
    // need the map for memoization purposes
    // key is the number, value is the result
    Map<Integer, Long> memoizationMap = new HashMap();
    Long result = dynamicApproach(0, memoizationMap);
    return result;
  }

  /**
   * Brute forces the solution and uses memoization.
   *
   * @param i given number
   * @param memoizationMap the map that stores previously calculated results
   * @return long for the given number
   */
  public long dynamicApproach(int i, Map<Integer, Long> memoizationMap) {
    long result = 0;
    if (i == input.size() - 1) {
      return 1;
    }
    // If we already previously calculated it for this input, simply retrieve and return it
    if (memoizationMap.containsKey(i)) {
      return memoizationMap.get(i);
    }
    for (int j = i + 1; j < input.size(); j++) {
      if ((input.get(j) - input.get(i)) <= 3) {
        result += dynamicApproach(j, memoizationMap);
      }
    }
    memoizationMap.put(i, result);
    return result;
  }

  /**
   * Prints the solutions for the day.
   */
  @Override
  public void printSolutions() {
    System.out.println("Part one: " + partOne());
    System.out.println("Part two: " + partTwo());
  }
}
