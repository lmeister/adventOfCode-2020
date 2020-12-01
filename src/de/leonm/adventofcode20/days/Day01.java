package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.utils.Reader;

import java.io.IOException;
import java.util.List;

public class Day01 {
    Reader reader;
    List<Integer> input;

    public Day01() {
        reader = new Reader();

        try {
            input = reader.getIntListFromFile("src/de/leonm/adventofcode20/input/day01.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int partOne(List<Integer> input) {
        int num1 = 0;
        int num2 = 0;
        for (int i : input) {
            for (int j : input) {
                if (i + j == 2020) {
                    num1 = i;
                    num2 = j;
                }
            }
        }
        return num1 * num2;
    }

    public int partTwo(List<Integer> input) {
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        for (int i : input) {
            for (int j : input) {
                for (int k : input) {
                    if (i + j +k == 2020) {
                        num1 = i;
                        num2 = j;
                        num3 = k;
                    }
                }
            }
        }
        return num1 * num2 * num3;
    }

    public void printSolutions() {
        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }
}
