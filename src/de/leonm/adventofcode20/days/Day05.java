package de.leonm.adventofcode20.days;

import de.leonm.adventofcode20.utils.BoardingPass;

import java.io.IOException;
import java.util.*;

public class Day05 extends Day {
    List<String> input;
    List<BoardingPass> boardingPasses;

    public Day05() {
        try {
            input = reader.getStringListFromFile("src/de/leonm/adventofcode20/input/day05.txt");
            boardingPasses = new ArrayList<>();
            for (String bp : input) {
                boardingPasses.add(new BoardingPass(bp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        return boardingPasses.parallelStream().mapToInt(BoardingPass::getSeatId).max().getAsInt();
    }

    public int partTwo() {
        int ourSeatID = 0;
        List<Integer> seatIds = new ArrayList<>();

        for (String bp : input) {
            BoardingPass boardingPass = new BoardingPass(bp);
            seatIds.add(boardingPass.getSeatId());
        }

        Collections.sort(seatIds);
        int lastId = -1;
        for (int currentId : seatIds) {
            if (currentId - lastId == 2) {
                //System.out.println("CurrentID: " + currentId + ", lastId: " + lastId);
                ourSeatID = currentId - 1;
                break;
            } else {
                lastId = currentId;
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
