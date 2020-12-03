package de.leonm.adventofcode20.days;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class Day03 extends Day {
    List<String> input;

    public Day03() {
        try {
            input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day03.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int partOne() {
        return calcTrees(input, 3, 1);
    }

    /**
     * Gives solution for part two
     * Uses a bigint as it would cause an overflow
     * @return Result als BigInt
     */
    public BigInteger partTwo() {
        BigInteger result = new BigInteger("1");
        result = result.multiply(new BigInteger("" + calcTrees(input, 1,1)));
        result = result.multiply(new BigInteger("" + calcTrees(input, 3,1)));
        result = result.multiply(new BigInteger("" + calcTrees(input, 5,1)));
        result = result.multiply(new BigInteger("" + calcTrees(input, 7,1)));
        result = result.multiply(new BigInteger("" + calcTrees(input, 1,2)));

        return result;
    }

    /**
     * Calculates the amount of trees given the slope
     * @param input The list of lines
     * @param slopeX the x-part of the slope (How many to the right)
     * @param slopeY the y-part of the slope (How many down)
     * @return the amount of trees encountered
     * TODO maybe refactor to use a list<pair<Integer, Integer>> for the slopes
     */
    public int calcTrees(List<String> input, int slopeX, int slopeY) {
        int treeCount = 0;
        int currentX = 0;
        for (int i = 0; i < input.size(); i += slopeY) {
            if (currentX > 0) {
                if (input.get(i).charAt(currentX % input.get(i).length()) == '#') {
                    treeCount++;
                }
            }
            currentX += slopeX;
        }
        return treeCount;
    }

    @Override
    public void printSolutions() {
        System.out.println("Part one: " + partOne());
        System.out.println("Part two: " + partTwo());
    }
}
