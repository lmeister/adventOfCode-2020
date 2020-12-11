package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11 extends Day {

  List<String> input;

  public Day11() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day11.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean isOccupied(char place) {
    final char occupied = '#';
    return place == occupied;
  }

  /**
   * This sounds pretty much like a cellular automaton. See Conway's game of life for an example.
   *
   * @param input
   * @return
   */
  public static char[][] iteration(char[][] input) {
    char[][] iterationResult = new char[input.length][input[0].length];

    //System.out.println("--- INPUT ---");
    //Arrays.stream(input).forEach(line -> System.out.println(line));

    for (int i = 0; i < iterationResult.length; i++) {
      for (int j = 0; j < iterationResult[0].length; j++) {
        List<Character> neighbors = getNeighbors(input, i, j);
        long occupiedNeighbors = neighbors.stream()
            .filter(Day11::isOccupied)
            .count();
        if (input[i][j] == '#') {
          occupiedNeighbors -= 1;
        }

        if (input[i][j] == 'L' && occupiedNeighbors == 0) {
          iterationResult[i][j] = '#';
        } else if (input[i][j] == '#' && occupiedNeighbors >= 4) {
          iterationResult[i][j] = 'L';
        } else {
          iterationResult[i][j] = input[i][j];
        }
      }
    }
    //System.out.println("--- OUTPUT ---");
    //Arrays.stream(iterationResult).forEach(line -> System.out.println(line));

    return iterationResult;
  }

  /**
   * Retries up to 8 neighbors for given point.
   *
   * @param matrix the input 2d matrix
   * @param x      x of given point
   * @param y      y of given point
   * @return a char array of neighbors of given point
   */
  public static List<Character> getNeighbors(char[][] matrix, int x, int y) {
    List<Character> chars = new ArrayList<>();

    int minX = ((x - 1) < 0) ? x : x - 1;
    int minY = ((y - 1) < 0) ? y : y - 1;
    int maxX = ((x + 1) >= matrix.length) ? x : x + 1;
    int maxY = ((y + 1) >= matrix[0].length) ? y : y + 1;

    for (int row = minX; row <= maxX; row++) {
      for (int col = minY; col <= maxY; col++) {
        //if (row != minX && col != minY) {
        chars.add(matrix[row][col]);
        //}
      }
    }
    return chars;
  }

  public long partOne() {
    char[][] matrix = new char[input.size()][input.get(0).length()];
    for (int i = 0; i < input.size(); i++) {
      for (int j = 0; j < input.get(i).length(); j++) {
        matrix[i][j] = input.get(i).charAt(j);
      }
    }

    char[][] priorResults;
    do {
      priorResults = matrix;
      matrix = iteration(matrix);

    } while (!Arrays.deepEquals(matrix, priorResults));

    return Arrays.stream(matrix)
        .map(CharBuffer::wrap)
        .flatMapToInt(CharBuffer::chars)
        .filter(c -> c == '#')
        .count();
  }

  /**
   * Prints the solutions for the day.
   */
  @Override
  public void printSolutions() {
    System.out.println("Part one: " + partOne());
    //System.out.println("Part two: " + partTwo());
  }
}
