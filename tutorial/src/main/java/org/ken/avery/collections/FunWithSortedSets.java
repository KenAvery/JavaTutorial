package org.ken.avery.collections;

import java.util.SortedSet;
import java.util.TreeSet;

public class FunWithSortedSets
{

    public static void main(final String[] args)
    {
        final SortedSet<String> raceHorseSet = new TreeSet<String>(RaceHorses.raceHorses().keySet());

        System.out.println(raceHorseSet);
        System.out.println(raceHorseSet.headSet("Handsomchamp"));
        System.out.println(raceHorseSet.headSet("Handsomchamp\0"));
        System.out.println(raceHorseSet.tailSet("Handsomchamp"));
        System.out.println(raceHorseSet.tailSet("Handsomchamp\0"));
        System.out.println(raceHorseSet.subSet("Handsomchamp\0", "Ormonde"));
        System.out.println(raceHorseSet.subSet("Handsomchamp", "Ormonde\0"));
        System.out.println(raceHorseSet.subSet("Handsomchamp", "Ormonde\0").size());
    }
}
