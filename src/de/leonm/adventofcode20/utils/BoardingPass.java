package de.leonm.adventofcode20.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoardingPass {

    private final Pattern PATTERN = Pattern.compile("(?<row>[FB]{7})(?<column>[LR]{3})");
    private Matcher matcher;

    private char[] row;
    private char[] column;

    public BoardingPass(String data) {
        matcher = PATTERN.matcher(data);
        if (matcher.find()) {
            row = matcher.group("row").toCharArray();
            column = matcher.group("column").toCharArray();
        }
    }

    private static int binaryPartition(char[] input) {
        int result = 0;

        for (char c : input) {
            result = result << 1; //Bitwise shift to the left
            if (c == 'B' || c == 'R') {
                result += 1;
            }
        }
        return result;
    }

    private int getRow() { return binaryPartition(row); }

    private int getColumn() { return binaryPartition(column); }

    /**
     * Formula as per task
     * @return
     */
    public int getSeatId() { return getRow() *  8 + getColumn(); }

    @Override
    public String toString() {
        return "BoardingPass { Row:" + getRow() + ", Column: " + getColumn() + ", SeatID: " + getSeatId() + " }";
    }
}
