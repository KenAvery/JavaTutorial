package org.ken.avery.collections.interfaces;

import java.util.SortedSet;
import java.util.TreeSet;

public class FunWithSortedSets
{

    public static void main(final String[] args)
    {
        final SortedSet<String> raceHorseSet = new TreeSet<String>(RaceHorses.raceHorses().keySet());

        System.out.println("Race Horse Set: " + raceHorseSet);
        System.out.println("Head Set starting at \"Handsomechamp\"       : " + raceHorseSet.headSet("Handsomchamp"));
        System.out.println("Head Set starting at \"Handsomechamp\\0\"     : " + raceHorseSet.headSet("Handsomchamp\0"));
        System.out.println("Tail Set starting at \"Ormonde\"             : " + raceHorseSet.tailSet("Ormonde"));
        System.out.println("Tail Set starting at \"Ormonde\\0\"           : " + raceHorseSet.tailSet("Ormonde\0"));
        System.out.println("Sub Set \"Handsomechamp\\0\", \"Ormonde\"       : " + raceHorseSet.subSet("Handsomchamp\0", "Ormonde"));
        System.out.println("Sub Set \"Handsomechamp\", \"Ormonde\\0\"       : " + raceHorseSet.subSet("Handsomchamp", "Ormonde\0"));
        System.out.println("Sub Set Size \"Handsomechamp\", \"Ormonde\\0\"  : "
                + raceHorseSet.subSet("Handsomchamp", "Ormonde\0").size());
        System.out.println("Sub Set First \"Handsomechamp\", \"Ormaonde\"  : "
                + raceHorseSet.subSet("Handsomchamp", "Ormonde").first());
        System.out.println("Sub Set Last \"Handsomechamp\", \"Ormonde\"    : "
                + raceHorseSet.subSet("Handsomchamp", "Ormonde").last());
    }
}
