package org.ken.avery.collections.interfaces;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TopRaceHorse
{
    public static void main(final String[] args)
    {
        final List<RaceHorse> raceHorses = loadRaceHorses(RaceHorses.raceHorses());

        System.out.println("Raw List: ");
        printRaceHorse(raceHorses);

        System.out.println("Shuffle List: ");
        Collections.shuffle(raceHorses, new Random());
        printRaceHorse(raceHorses);

        System.out.println("Sort List: ");
        Collections.sort(raceHorses, new CompareRaceHorses());
        printRaceHorse(raceHorses);

        System.out.println();
        System.out.println("The Top Race Horse: " + raceHorses.get(0).getRaceHorse());
    }

    private static void printRaceHorse(final List<RaceHorse> raceHorses)
    {
        for (final RaceHorse raceHorse : raceHorses)
        {
            System.out.format("Race Horse: %s, Races Won %d%n", raceHorse.getRaceHorse(), raceHorse.getRacesWon());
        }
    }

    private static List<RaceHorse> loadRaceHorses(final Map<String, Integer> map)
    {
        final List<RaceHorse> raceHorses = new LinkedList<RaceHorse>();
        for (final Map.Entry<String, Integer> entry : map.entrySet())
        {
            final RaceHorse raceHorse = new RaceHorse(entry.getKey(), entry.getValue());
            raceHorses.add(raceHorse);
        }
        return raceHorses;
    }
}
