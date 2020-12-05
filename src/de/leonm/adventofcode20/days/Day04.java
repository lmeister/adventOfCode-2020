package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.models.Passport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Refactoring thoughts:
 * Passport class should only be a DTO
 * -> Should not have isValidate() due to separation of responsibility
 * -> Maybe only create a Map for each Passport and check for existing stuff there?
 * -> Keep Passport class and introduce logic to the constructor so that we validate inputs
 *    (not ranges, merely format)
 * Probably change the validation to include more RegEx
 * -> This might make it faster, however less flexible (New ranges can't be introduced as easily)
 * Logic in isValidPassport is very ugly due to extensive if-else branches
 * -> Should be made neater somehow (Switch case?)
 */
public class Day04 extends Day {
  List<String> input;
  List<Passport> passports;

  /**
   * Constructor for Day04.
   * Reads the input and also initializes the passport list.
   */
  public Day04() {
    try {
      input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day04.txt");
      passports = new ArrayList<>();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Solves part01.
   *
   * @return the amount of valid passports.
   */
  public long partOne() {
    passports = readPassports(false);
    return passports.stream().filter(p -> p.isValidPassport(false)).count();
  }

  /**
   * Solves part02.
   *
   * @return the amount of valid passports.
   */
  public long partTwo() {
    passports = readPassports(true);
    return passports.stream().filter(p -> p.isValidPassport(true)).count();
  }

  /**
   * Creates a list of passports depending on the criteria.
   *
   * @param strict Should be true for part two, false for part01.
   * @return a list of valid passports.
   */
  public List<Passport> readPassports(boolean strict) {
    Passport passport = new Passport();
    List<Passport> passports = new ArrayList<>();

    for (String p : input) {
      if (p.isBlank()) {
        passports.add(passport);
        passport = new Passport();
      } else {
        passport.addData(p);
      }
    }

    if (passport.isValidPassport(strict)) {
      passports.add(passport);
    }
    return passports;
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
