package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.models.HandheldGameConsole;
import de.leonm.adventofcode20.models.Instruction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day08 extends Day {
  List<Instruction> instructions;

  public Day08() {
    try {
      instructions = new ArrayList<>();
      reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day08.txt")
          .forEach(l -> instructions.add(new Instruction(l)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int partOne() {
    int result = 0;

    HandheldGameConsole gameConsole = new HandheldGameConsole(instructions);
    result = gameConsole.run();

    return result;
  }


  /**
   * Prints the solutions for the day.
   */
  @Override
  public void printSolutions() {
    System.out.println("Part one: " + partOne());
    // System.out.println("Part two: " + partTwo());
  }
}
