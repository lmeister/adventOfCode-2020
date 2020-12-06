package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Hasn't been refactored yet.
 */
public class Day06 extends Day {
  List<String> input;

  /** Constructor for Day06. Reads the input into a string list. */
  public Day06() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day06.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solves part one of day6.
   *
   * @return the amount of answers where anyone answered yes. */
  public int partOne() {
    Set<String> answers = new HashSet<>();
    int result = 0;

    for (String person : input) {
      if (!person.isBlank()) {
        for (int i = 0; i < person.length(); i++) {
          answers.add(Character.toString(person.charAt(i)));
        }
      } else {
        result += answers.size();
        answers.clear();
      }
    }
    return result + answers.size();
  }

  /**
   * Solves part two of day6.
   *
   * @return the amount of answers where everyone answered yes.
   */
  public int partTwo() {
    Map<String, Integer> answers = new HashMap<>();
    int result = 0;
    int personCounter = 0;

    for (String person : input) {
      if (!person.isBlank()) {
        personCounter++;
        for (int i = 0; i < person.length(); i++) {
          String curChar = Character.toString(person.charAt(i));
          if (answers.containsKey(curChar)) {
            answers.put(curChar, answers.get(curChar) + 1);
          } else {
            answers.put(curChar, 1);
          }
        }
      } else {
        result += getUnanimousAnswers(answers, personCounter);
        answers.clear();
        personCounter = 0;
      }
    }
    result += getUnanimousAnswers(answers, personCounter);
    return result;
  }

  /**
   * Finds the amount of questions where people unanimously voted yes.
   *
   * @param answers the map holding all the answers + amount of people
   * @param groupSize the size of the group
   * @return returns amount of questions which were unanimously voted yes.
   */
  public int getUnanimousAnswers(Map<String, Integer> answers, int groupSize) {
    int result = 0;
    for (String key : answers.keySet()) {
      if (answers.get(key) == groupSize) {
        result++;
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
