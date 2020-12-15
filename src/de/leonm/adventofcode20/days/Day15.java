package de.leonm.adventofcode20.days;

import java.util.HashMap;

public class Day15 extends Day {

  final int[] startingNumbers = {5, 2, 8, 16, 18, 0, 1};
  HashMap<Integer, Integer> numbers;


  public Day15() {
    numbers = new HashMap<>();

    // dont add last one to the map yet
    for (int i = 0; i < startingNumbers.length - 1; i++) {
      numbers.put(startingNumbers[i], i);
    }
  }

  public int partOne() {
    final long startTime = System.currentTimeMillis();
    int result = getNumberAt(2020);
    System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
    return result;
  }

  public int partTwo() {
    final long startTime = System.currentTimeMillis();
    int result = getNumberAt(30000000);
    System.out.println((System.currentTimeMillis() - startTime) / 1000.0);
    return result;
  }

  public int getNumberAt(int index) {
    numbers.clear();

    // dont add last one to the map yet
    for (int i = 0; i < startingNumbers.length - 1; i++) {
      numbers.put(startingNumbers[i], i);
    }
    int lastNumber = startingNumbers[startingNumbers.length - 1];

    for (int turn = numbers.size(); turn <= index - 2; turn++) {
      int newLast;
      if (!numbers.containsKey(lastNumber)) {
        newLast = 0;
      } else {
        newLast = turn - numbers.get(lastNumber);
      }

      numbers.put(lastNumber, turn);
      lastNumber = newLast;
    }
    return lastNumber;
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
