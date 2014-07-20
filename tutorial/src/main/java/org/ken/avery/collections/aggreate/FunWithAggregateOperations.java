package org.ken.avery.collections.aggreate;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.ken.avery.collections.interfaces.RaceHorse;
import org.ken.avery.collections.interfaces.UndefetedRaceHorses;
import org.ken.avery.collections.interfaces.TopRaceHorse;

public class FunWithAggregateOperations
{

    public static void main(final String[] args)
    {
        final List<RaceHorse> raceHorses = TopRaceHorse.loadRaceHorses(UndefetedRaceHorses.raceHorses());

        final double count = raceHorses.stream().mapToInt(RaceHorse::getRacesWon).count();
        System.out.println("The total number of race horses: " + count);

        System.out.print("Race Horses: ");
        raceHorses.stream().forEach(raceHorse -> System.out.format("%s, ", raceHorse.getRaceHorse()));
        System.out.println();

        System.out.print("Race Horses with over 20 wins: ");
        raceHorses.stream().filter(raceHorse -> raceHorse.getRacesWon() > 20)
        .forEach(raceHorse -> System.out.format("%s, ", raceHorse.getRaceHorse()));
        System.out.println();

        final double average = raceHorses.stream().mapToInt(RaceHorse::getRacesWon).average().getAsDouble();
        System.out.println("The average wins for all race horses: " + average);

        final OptionalInt min = raceHorses.stream().mapToInt(RaceHorse::getRacesWon).min();
        System.out.println("The minimum wins for a race horse: " + min.getAsInt());

        final OptionalInt max = raceHorses.stream().mapToInt(RaceHorse::getRacesWon).max();
        System.out.println("The maximum wins for a race horses: " + max.getAsInt());

        final double total = raceHorses.stream().mapToInt(RaceHorse::getRacesWon).sum();
        System.out.println("The total wins for all race horses: " + total);

        final Optional<Integer> reductionSum = raceHorses.stream().map(RaceHorse::getRacesWon).reduce((a, b) -> a + b);
        System.out.println("The total wins for all race horses using reduce: " + reductionSum.get());

        final Averager averageCollect = raceHorses.stream().filter(raceHorse -> raceHorse.getRacesWon() > 9)
                .map(RaceHorse::getRacesWon).collect(Averager::new, Averager::accept, Averager::combine);
        System.out.println("Average races won greater than 9: " + averageCollect.average());

        final List<String> topWinners = raceHorses.stream().filter(raceHorse -> raceHorse.getRacesWon() > 20)
                .map(raceHorse -> raceHorse.getRaceHorse()).collect(Collectors.toList());
        System.out.println("Top winners: " + topWinners);
    }
}
