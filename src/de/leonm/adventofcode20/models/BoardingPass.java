package de.leonm.adventofcode20.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoardingPass {

  private static final Pattern PATTERN = Pattern.compile("(?<row>[FB]{7})(?<column>[LR]{3})");
  private Matcher matcher;

  private char[] row;
  private char[] column;

  /**
   * Constructor for the boarding pass class.
   * Only creates boarding pass if input is valid.
   *
   * @param data the string of input data, must match [FB]{7}[LR]{3}
   */
  public BoardingPass(String data) {
    matcher = PATTERN.matcher(data);
    if (matcher.find()) {
      row = matcher.group("row").toCharArray();
      column = matcher.group("column").toCharArray();
    }
  }

  /**
   * Will perform the binary partition Uses bitwise shifting operations.
   * Note that the 'B' and 'R', meaning we take the "upper half".
   * Could be changed to parameters, to have universal soutions.
   *
   * @param input a character array consisting of either [FB]{7} or [LR]{3}
   * @return the resulting number
   */
  private static int binaryPartition(char[] input) {
    int result = 0;

    for (char c : input) {
      result = result << 1; // Bitwise shift to the left
      if (c == 'B' || c == 'R') {
        result += 1;
      }
    }
    return result;
  }

  /**
   * Gets the row number Calls binaryPartition.
   *
   * @return the row number as integer
   */
  private int getRow() {
    return binaryPartition(row);
  }

  /**
   * Gets the column number Calls binaryPartition.
   *
   * @return the column number as integer
   */
  private int getColumn() {
    return binaryPartition(column);
  }

  /**
   * Formula as per task.
   *
   * @return seatId as integer
   */
  public int getSeatId() {
    return getRow() * 8 + getColumn();
  }

  @Override
  public String toString() {
    return "BoardingPass { Row:"
        + getRow()
        + ", Column: "
        + getColumn()
        + ", SeatID: "
        + getSeatId()
        + " }";
  }
}
