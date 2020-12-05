package de.leonm.adventofcode20.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {

  public Stream<String> getStringStreamFromFile(String path) throws IOException {
    return Files.lines(Paths.get(path));
  }

  public List<Integer> getIntListFromFile(String path) throws IOException {
    return getStringStreamFromFile(path).map(Integer::parseInt).collect(Collectors.toList());
  }

  public List<String> getStringListFromFile(String path) throws IOException {
    return Files.readAllLines(Paths.get(path));
  }
}
