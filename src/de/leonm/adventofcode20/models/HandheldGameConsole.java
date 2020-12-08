package de.leonm.adventofcode20.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;


public class HandheldGameConsole {
  private int accumulator;
  private List<Instruction> instructions;
  private int instructionPointer;
  private HashSet<Integer> visitedAdresses;


  public HandheldGameConsole(Deque input) {
    instructions = new ArrayList<>(input);
    visitedAdresses = new HashSet<>();
  }

  public int run() {
    instructionPointer = 0;
    do {
      Instruction currentInstruction = instructions.get(instructionPointer);
      System.out.println("Current Instruction: "
          + currentInstruction.getInstruction() + " "
          + currentInstruction.getValue());
      switch (currentInstruction.getInstruction()) {
        case "nop":
          visitedAdresses.add(instructionPointer);
          instructionPointer++;
          break;
        case "acc":
          accumulator += currentInstruction.getValue();
          visitedAdresses.add(instructionPointer);
          instructionPointer++;
          break;
        case "jmp":
          visitedAdresses.add(instructionPointer);
          instructionPointer += currentInstruction.getValue();
          break;
        default:
          System.out.println("Invalid instruction");
          visitedAdresses.add(instructionPointer);
          instructionPointer++;
      }
    } while (!visitedAdresses.contains(instructionPointer));
    return accumulator;
  }

}
