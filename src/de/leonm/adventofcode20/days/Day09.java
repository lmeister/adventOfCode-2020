package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Day09 extends Day {
  List<String> input;
  List<Long> numbers;

  /** Constructor for Day09. */
  public Day09() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day09.txt");
      numbers = new ArrayList<>();
      input.stream().forEach(n -> numbers.add(Long.valueOf(n)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solves part one of day 9.
   *
   * @return Returns the number that does not fulfill the property. If not found, returns -1
   */
  public long partOne() {
    ArrayDeque candidates = new ArrayDeque(numbers.subList(0, 25));
    for (int i = 25; i <= numbers.size(); i++) {
      if (!findIfSumPossible(candidates, numbers.get(i))) {
        return numbers.get(i);
      }
      candidates.removeFirst();
      candidates.add(numbers.get(i));
    }
    return -1;
  }

  /**
   * Solves part two of day09.
   *
   * @return the sum of the smallest element and the biggest element of the sublist
   */
  public long partTwo() {
    long sum = partOne();
    List<Long> subList = findSublistAddingToSum(numbers, sum);
    long max = subList.stream().max(Long::compareTo).orElse(0L);
    long min = subList.stream().min(Long::compareTo).orElse(0L);
    return min + max;
  }

  /**
   * Finds if there is a pair in a list that adds to given sum. Will run in linear time due to usage
   * of a set.
   *
   * @param input The list that is searched
   * @param sum the given sum
   * @return a boolean whether or not there are candidates
   */
  public boolean findIfSumPossible(Collection<Long> input, long sum) {
    HashSet<Long> candidates = new HashSet<>();
    long num1 = 0L;
    long num2 = 0L;

    for (Long i : input) {
      long match = sum - i;
      if (candidates.contains(match)) {
        num1 = i;
        num2 = match;
        break;
      }
      candidates.add(i);
    }
    return (num1 + num2) == sum;
  }

  /**
   * Finds a sublist of a list adding to a given sum. This function uses sort of a sliding window
   * mechanism. We keep increasing the size of our subList as long as the subList's sum < given Sum.
   * If the sublistSum > given Sum we will have to remove the first element of our subList. If the
   * sublistSum < given Sum we will have to add another element to the end of our sublist. This will
   * only work for positive numbers and will run in linear time.
   *
   * @param input The list that we will look for sublist in
   * @param sum the sum that is to be matched
   * @return a List of BigIntegers
   */
  public List<Long> findSublistAddingToSum(List<Long> input, long sum) {
    long currentSum = input.get(0);
    int startOfSubArray = 0;
    int endOfSubArray = 0;
    List<Long> result = new ArrayList<>();

    // Fülle die subliste so lange die summe der subliste < sum
    while (startOfSubArray < input.size() && endOfSubArray <= input.size()) {
      // Target is reached
      if (currentSum == sum) {
        result = input.subList(startOfSubArray, endOfSubArray);
        break;
      } else {
        // greater
        if (currentSum > sum) {
          currentSum = currentSum - input.get(startOfSubArray);
          startOfSubArray++;
        } else {
          if (endOfSubArray < input.size() - 1) {
            endOfSubArray++;
            currentSum = currentSum + input.get(endOfSubArray);
          }
        }
      }
    }
    return result;
  }

  /** Prints the solutions for the day. */
  @Override
  public void printSolutions() {
    System.out.println("Part one: " + partOne());
    System.out.println("Part two: " + partTwo());
  }
}
