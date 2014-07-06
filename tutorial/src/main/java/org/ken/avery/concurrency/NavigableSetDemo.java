package org.ken.avery.concurrency;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetDemo
{

    public static void main(final String[] args)
    {

        final NavigableSet<String> navigableSet = new TreeSet<String>(Arrays.asList("X", "B", "A", "Z", "T"));

        Iterator<String> iterator = navigableSet.descendingIterator();
        System.out.print("Original Set        : ");
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();

        iterator = navigableSet.iterator();
        System.out.print("Sorted Navigable Set: ");
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + ", ");
        }
        System.out.format("%n%n");

        System.out.printf("Head Set (X)               : %s.%n", navigableSet.headSet("X"));
        System.out.printf("Tail Set (T, true)         : %s.%n", navigableSet.tailSet("T", false));
        System.out.printf("Sub Set  (B, true, X, true): %s.%n", navigableSet.subSet("B", true, "X", true));
        System.out.printf("Last Element               : %s%n", navigableSet.last());
        System.out.printf("First Element              : %s%n", navigableSet.first());
        System.out.printf("Reverse Set                : %s%n", navigableSet.descendingSet());
        System.out.printf("Original Set               : %s%n", navigableSet);
    }
}
