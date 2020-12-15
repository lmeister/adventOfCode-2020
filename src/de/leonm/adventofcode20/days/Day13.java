package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day13 extends Day {

  List<String> input;

  public Day13() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/Day13.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int partOne() {
    int earliestTime = Integer.parseInt(input.get(0));

    List<Integer> busIds =
        Arrays.stream(input.get(1).split(","))
            .filter(s -> !s.equals("x"))
            .map(Integer::valueOf)
            .collect(Collectors.toList());

    boolean busFound = false;
    for (int i = earliestTime; !busFound; i++) {
      for (int busId : busIds) {
        if (i % busId == 0) {
          System.out.println("Bus found: " + busId + ", at: " + i);
          busFound = true;
          return busId * (i - earliestTime);
        }
      }
    }
    return 1;
  }

  public long partTwo() {
    String[] busIds = input.get(1).split(",");

    long[][] nums = IntStream.range(0, busIds.length).filter(i -> !busIds[i].equals("x"))
        .mapToObj(i -> new long[]{Long.parseLong(busIds[i]), i})
        .toArray(long[][]::new);

    long product = Arrays.stream(nums).mapToLong(a -> a[0]).reduce((a, b) -> a * b).getAsLong();

    // Chinese remainder theorem
    // https://www.freecodecamp.org/news/how-to-implement-the-chinese-remainder-theorem-in-java-db88a3f1ffe0/
    long sum = Arrays.stream(nums)
        .mapToLong(a -> a[1] * (product / a[0]) * inverseModulo(product / a[0], a[0])).sum();

    return product - sum % product;
  }

  /**
   * Basically extended euclidean algorithm. https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
   *
   * @param a
   * @param b
   * @return
   */
  public long inverseModulo(long a, long b) {
    long res = 0;
    if (a != 0) {
      res = b % a;
      if (res == 0) {
        res = 1;
      } else {
        res = b - inverseModulo(res, a) * b / a;
      }
    }
    return res;
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
