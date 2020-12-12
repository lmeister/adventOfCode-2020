package de.leonm.adventofcode20.days;

import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 extends Day {

  List<Instruction> input;

  public Day12() {
    try {
      input = reader.getStringStreamFromFile("src/de/leonm/adventofcode20/input/Day12.txt")
          .map(line -> new Instruction(line.charAt(0), Integer.parseInt(line.substring(1))))
          .collect(
              Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public int partOne() {
    char currentlyFacing = 'E';
    Point pos = new Point(0, 0);

    for (Instruction inst : input) {
      // System.out.println("Dir: " + inst.getInstruction() + ", Value: " + inst.getValue());

      switch (inst.getInstruction()) {
        case 'F':
          if (currentlyFacing == 'E') {
            pos.x += inst.getValue();
          } else if (currentlyFacing == 'W') {
            pos.x -= inst.getValue();
          } else if (currentlyFacing == 'N') {
            pos.y += inst.getValue();
          } else if (currentlyFacing == 'S') {
            pos.y -= inst.getValue();
          }
          break;
        case 'N':
          pos.y += inst.getValue();
          break;
        case 'S':
          pos.y -= inst.getValue();
          break;
        case 'E':
          pos.x += inst.getValue();
          break;
        case 'W':
          pos.x -= inst.getValue();
          break;
        // Handle 'L' and 'R' the same
        case 'L':
        case 'R':
          currentlyFacing = updateDirection(inst.getInstruction(), currentlyFacing,
              inst.getValue());
          break;
        default:
      }

    }
    return Math.abs(pos.x) + Math.abs(pos.y);
  }

  public int partTwo() {
    Point pos = new Point(0, 0);
    Point waypoint = new Point(10, 1);

    for (Instruction inst : input) {
      switch (inst.getInstruction()) {
        // This is the only case in which we move the ship
        case 'F':
          // create waypoint with relative coordinates
          Point relativeWaypoint = new Point(waypoint.x - pos.x,
              waypoint.y - pos.y);
          pos = new Point(pos.x + ((waypoint.x - pos.x) * inst.getValue()),
              pos.y + ((waypoint.y - pos.y) * inst.getValue()));
          // give waypoint new position relative to ship
          waypoint = new Point(pos.x + relativeWaypoint.x, pos.y + relativeWaypoint.y);
          break;
        case 'N':
          waypoint.y += inst.getValue();
          break;
        case 'S':
          waypoint.y -= inst.getValue();
          break;
        case 'E':
          waypoint.x += inst.getValue();
          break;
        case 'W':
          waypoint.x -= inst.getValue();
          break;
        case 'L':
          int value = inst.getValue();
          while (value > 0) {
            relativeWaypoint = new Point(waypoint.x - pos.x, waypoint.y - pos.y);
            waypoint = new Point(pos.x - relativeWaypoint.y, pos.y + relativeWaypoint.x);
            value -= 90;
          }
          break;
        case 'R':
          value = inst.getValue();
          while (value > 0) {
            relativeWaypoint = new Point(waypoint.x - pos.x,
                waypoint.y - pos.y);
            waypoint = new Point(pos.x + relativeWaypoint.y, pos.y - relativeWaypoint.x);
            value -= 90;
          }
          break;
        default:
      }
      //System.out.println("Instruction: " + inst.getInstruction() + inst.getValue());
      //System.out.println("Ship: " + pos.x + ",  " + pos.y);
      //System.out.println("Waypoint: " + waypoint.x + ", " + waypoint.y);
      //System.out.println("");
    }
    //System.out.println("X " + pos.x + ", Y: " + pos.y);
    return Math.abs(pos.x) + Math.abs(pos.y);
  }

  public char updateDirection(char dir, char current, int value) {
    char result = current;
    int remainingValue = value;
    if (dir == 'L') {
      while (remainingValue > 0) {
        if (result == 'E') {
          result = 'N';
        } else if (result == 'N') {
          result = 'W';
        } else if (result == 'W') {
          result = 'S';
        } else if (result == 'S') {
          result = 'E';
        }
        remainingValue -= 90;
      }
    } else if (dir == 'R') {
      while (remainingValue > 0) {
        if (result == 'E') {
          result = 'S';
        } else if (result == 'N') {
          result = 'E';
        } else if (result == 'W') {
          result = 'N';
        } else if (result == 'S') {
          result = 'W';
        }
        remainingValue -= 90;
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

  private class Instruction {

    char instruction;
    int value;

    public Instruction(char instruction, int value) {
      this.instruction = instruction;
      this.value = value;
    }

    public char getInstruction() {
      return instruction;
    }

    public int getValue() {
      return value;
    }
  }
}
