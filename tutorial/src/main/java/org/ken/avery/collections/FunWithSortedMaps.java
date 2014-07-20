package org.ken.avery.collections;

import java.util.SortedMap;
import java.util.TreeMap;

public class FunWithSortedMaps
{

    public static void main(final String[] args)
    {
        final SortedMap<String, Integer> raceHorseMap = new TreeMap<String, Integer>(RaceHorses.raceHorses());

        System.out.println("Race Horse Map: " + raceHorseMap);
        System.out.println("Head Map starting at \"Highflyer\"       : " + raceHorseMap.headMap("Highflyer"));
        System.out.println("Head Map starting at \"Highflyer\\0\"     : " + raceHorseMap.headMap("Highflyer\0"));
        System.out.println("Tail Map starting at \"Peppers\"         : " + raceHorseMap.tailMap("Peppers"));
        System.out.println("Tail Map starting at \"Peppers\\0\"       : " + raceHorseMap.tailMap("Peppers\0"));
        System.out.println("Sub Map \"Highflyer\\0\", \"Peppers\"       : " + raceHorseMap.subMap("Highflyer\0", "Peppers"));
        System.out.println("Sub Map \"Highflyer\", \"Peppers\\0\"       : " + raceHorseMap.subMap("Highflyer", "Peppers\0"));
        System.out.println("Sub Map Size \"Highflyer\", \"Peppers\\0\"  : "
                + raceHorseMap.subMap("Highflyer", "Peppers\0").size());
        System.out.println("Sub Map First \"Highflyer\", \"Ormaonde\"  : "
                + raceHorseMap.subMap("Highflyer", "Peppers").firstKey());
        System.out.println("Sub Map Last \"Highflyer\", \"Peppers\"    : "
                + raceHorseMap.subMap("Highflyer", "Peppers").lastKey());
    }
}
