package de.leonm.adventofcode20;

import de.leonm.adventofcode20.days.Day;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            System.out.println("-------- Day " + i + " --------");
            try {
                Day day = (Day) Class.forName("de.leonm.adventofcode20.days.Day0" + i).getDeclaredConstructor().newInstance();
                day.printSolutions();
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException cne) {
                System.out.println(cne);
            }
        }
    }
}
