package org.ken.avery.arrays;

import java.util.Arrays;

public class ArraySortDemo
{
    public static void main(final String[] args)
    {

        // initializing unsorted int array
        final int iArr[] = {
                2, 1, 9, 6, 4
        };

        // let us print all the elements available in list
        for (final int number : iArr)
        {
            System.out.println("Number = " + number);
        }

        // sorting array
        Arrays.sort(iArr);

        // let us print all the elements available in list
        System.out.println("The sorted int array is:");
        for (final int number : iArr)
        {
            System.out.println("Number = " + number);
        }
    }
}
