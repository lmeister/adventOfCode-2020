package de.leonm.adventofcode20.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instruction {
  private static final String PATTERN = "(?<instruction>\\w{3}) (?<value>[+-]\\d+)";

  private String instruction;
  private int value;


  public Instruction(String instruction) {
    Pattern pattern = Pattern.compile(PATTERN);
    Matcher matcher = pattern.matcher(instruction);
    if (matcher.find()) {
      setInstruction(matcher.group("instruction"));
      setValue(Integer.parseInt(matcher.group("value")));
    }
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
