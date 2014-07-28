package org.ken.avery.generics;

import java.util.Collections;
import java.util.Set;

public class CollectionEmptySetDemo
{
    public static void main(final String[] args)
    {
        final Set<String> emptySetA = Collections.<String> emptySet();
        final Set<String> emptySetB = Collections.emptySet();

        if (emptySetA == emptySetB)
        {
            System.out.println("Both Sets A and B are equal");
        }

        printWords(Collections.<String> emptySet());
    }

    public static void printWords(final Set<String> stringSet)
    {
        System.out.println("Printing an empty set of strings: " + stringSet);
    }

}
