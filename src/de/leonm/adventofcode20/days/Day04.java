package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.utils.Passport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Day04 extends Day {
    List<String> input;
    List<Passport> passports;

    public Day04() {
        try {
            input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day04.txt");
            passports = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long partOne() {
        passports = readPassports(false);
        return passports.stream().filter(p -> p.isValidPassport(false)).count();
    }

    public long partTwo() {
        passports = readPassports(true);
        return passports.stream().filter(p -> p.isValidPassport(true)).count();
    }

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

    @Override
    public void printSolutions() {
        System.out.println("Part one: " + partOne());
        System.out.println("Part two: " + partTwo());
    }
}
