package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.models.HandheldGameConsole;
import de.leonm.adventofcode20.models.Instruction;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;

public class Day08 extends Day {
  List<String> input;


  public Day08() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day08.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int partOne() {
    ArrayDeque<Instruction> instructions = new ArrayDeque<>();
    input.forEach(i -> instructions.add(new Instruction((i))));
    HandheldGameConsole gameConsole = new HandheldGameConsole(instructions);

    return gameConsole.run();
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
