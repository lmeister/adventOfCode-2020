package de.leonm.adventofcode20;

import de.leonm.adventofcode20.days.Day;
import java.lang.reflect.InvocationTargetException;

public class Main {

  /**
   * Main method to instantiate all the day objects and print their solutions.
   *
   * @param args Standard program args.
   */
  public static void main(String[] args) {
    for (int i = 1; i <= 5; i++) {
      String dayNumberCode = String.format("%02d", i);
      System.out.println("-------- Day " + dayNumberCode + " --------");
      try {
        Day day =
            (Day)
                Class.forName("de.leonm.adventofcode20.days.Day" + dayNumberCode)
                    .getDeclaredConstructor()
                    .newInstance();
        day.printSolutions();
      } catch (ClassNotFoundException
          | NoSuchMethodException
          | InstantiationException
          | IllegalAccessException
          | InvocationTargetException cne) {
        System.out.println(cne);
      }
    }
  }
}
