package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.models.BoardingPass;
import de.leonm.adventofcode20.utils.NanoClock;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Day05 extends Day {
    List<String> input;
    List<BoardingPass> boardingPasses;

    public Day05() {
        try {
            input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day05.txt");
            boardingPasses = input.stream().map(BoardingPass::new).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Solves part one of day05
     * Iterates through all the boardingpasses and retrieves their seat id
     * will then retrieve the maximum utilizing the streaming api
     * @return the highest seatId as integer - will return -1 if no max is found
     */
    public int partOne() {
        /*
         * Some Tests
        BoardingPass testPass = new BoardingPass("BFFFBBFRRR");
        System.out.println(testPass); // should be 70, 7, 567
        testPass = new BoardingPass("FFFBBBFRRR");
        System.out.println(testPass); // should be 14, 7, 119
        testPass = new BoardingPass("BBFFBBFRLL");
        System.out.println(testPass); // should be 102, 4,
         */

        /*
         * Test whether parallelstream or normal stream is faster
         * Not that this is not nearly as accurate as profiling, just a quick glance
        */
        /*
        System.out.println("--Test with normal stream--");
        final Clock clock = new NanoClock();
        final Instant instantBegin = Instant.now(clock);
        System.out.println("Result: " + boardingPasses.stream().mapToInt(BoardingPass::getSeatId).max().orElse(-1));
        final Instant instantEnd = Instant.now(clock);
        System.out.println("Difference: " + (instantEnd.getNano() - instantBegin.getNano()) + " nanoseconds");

        System.out.println("--Test with parallel stream--");
        final Instant instantParBegin = Instant.now(clock);
        System.out.println("Result: " + boardingPasses.parallelStream().mapToInt(BoardingPass::getSeatId).max().orElse(-1));
        final Instant instantParEnd = Instant.now(clock);
        System.out.println("Difference: " + (instantParEnd.getNano() - instantParBegin.getNano()) + " nanoseconds");
        */

        return boardingPasses.stream().mapToInt(BoardingPass::getSeatId).max().orElse(-1);
    }

    /**
     * Solves part two of day05
     * Create a Set of the existing seat ids and iterates through it
     * checks for every current element curId that curId + 2 exist and curId + 1 do not exist
     * if the condition holds true, we break the loop and return curId + 1 as our seat id
     * @return our seatId as integer
     */
    public int partTwo() {
        int ourSeatID = 0;
        Set<Integer> seatIds = boardingPasses.stream()
                .map(BoardingPass::getSeatId)
                .collect(Collectors.toSet());

        for (int curId : seatIds) {
            if (!seatIds.contains(curId + 1) && seatIds.contains(curId + 2)) {
                ourSeatID = curId + 1;
                break;
            }
        }
        return ourSeatID;
    }

    @Override
    public void printSolutions() {
        System.out.println("Part one: " + partOne());
        System.out.println("Part two: " + partTwo());
    }
}
