package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

public class Day09 extends Day {
  List<String> input;
  List<BigInteger> numbers;

  public Day09() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day09.txt");
      numbers = new ArrayList<>();
      input.stream().forEach(n -> numbers.add(new BigInteger(n)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public BigInteger partOne() {
    ArrayDeque candidates = new ArrayDeque(numbers.subList(0, 25));
    for (int i = 25; i <= numbers.size(); i++) {
      if (!findIfSumPossible(candidates, numbers.get(i))) {
        return numbers.get(i);
      }
      candidates.removeFirst();
      candidates.add(numbers.get(i));
    }
    return new BigInteger("-1");
  }

  public BigInteger partTwo() {
    BigInteger sum = partOne();
    List<BigInteger> subList = findSublistAddingToSum(numbers, sum);
    BigInteger max = subList.stream().max(BigInteger::compareTo).orElse(new BigInteger("0"));
    BigInteger min = subList.stream().min(BigInteger::compareTo).orElse(new BigInteger("0"));
    return new BigInteger("" + min.add(max));
  }


  public boolean findIfSumPossible(Collection<BigInteger> input, BigInteger sum) {
    HashSet<BigInteger> candidates = new HashSet<>();
    BigInteger num1 = new BigInteger("0");
    BigInteger num2 = new BigInteger("0");

    for (BigInteger i : input) {
      BigInteger match = sum.subtract(i);
      if (candidates.contains(match)) {
        num1 = i;
        num2 = match;
        break;
      }
      candidates.add(i);
    }
    return ((num1.add(num2)).compareTo(sum) == 0);
  }

  public List<BigInteger> findSublistAddingToSum(List<BigInteger> input, BigInteger sum) {
    BigInteger currentSum = input.get(0);
    int startOfSubArray = 0;
    int endOfSubArray = 0;
    List<BigInteger> result = new ArrayList<>();

    // Gehe durch input
    for (int i = 0; i < input.size(); i++) {
      // Fülle die subliste so lange die summe der subliste < sum
      while (startOfSubArray < input.size() && endOfSubArray <= input.size()) {
        // Target is reached
        if (currentSum.compareTo(sum) == 0) {
          result = input.subList(startOfSubArray, endOfSubArray);
          break;
        } else {
          // greater
          if (currentSum.compareTo(sum) == 1) {
            currentSum = currentSum.subtract(input.get(startOfSubArray));
            startOfSubArray++;
          } else {
            if (endOfSubArray < input.size() - 1) {
              endOfSubArray++;
              currentSum = currentSum.add(input.get(endOfSubArray));
            }
          }
        }

      }
    }
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
