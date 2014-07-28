package org.ken.avery.generics;

import java.util.Collection;

import org.ken.avery.collections.interfaces.Colors;
import org.ken.avery.collections.interfaces.UndefetedRaceHorses;

public class FactoryMethodsUse
{

    public static void main(final String[] args)
    {
        final Collection<String> horseNames = FactoryMethods.mapKeysToCollection(String.class, UndefetedRaceHorses.raceHorses());
        final Collection<Integer> colorNumbers = FactoryMethods.mapKeysToCollection(Integer.class, Colors.colors());
        final Collection<Integer> horseWins = FactoryMethods.mapValuesToCollection(Integer.class, UndefetedRaceHorses.raceHorses());
        final Collection<String> color = FactoryMethods.mapValuesToCollection(String.class, Colors.colors());

        FactoryMethods.printCollection("Hrose Names", horseNames);
        FactoryMethods.printCollection("Horse Wins", horseWins);
        FactoryMethods.printCollection("Color Numbers", colorNumbers);
        FactoryMethods.printCollection("Color", color);

        System.out.println("Sum of Horse Wins: " + FactoryMethods.sumCollection(horseWins));
    }
}
