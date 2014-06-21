package org.ken.avery.arrays;

import java.util.Arrays;

public class ArrayBinarySearchDemo
{
    public static void main(final String[] args)
    {

        // initializing unsorted int array
        final int intArr[] = {
                30, 20, 5, 12, 55
        };

        // sorting array
        Arrays.sort(intArr);

        // let us print all the elements available in list
        System.out.println("The sorted int array is:");
        for (final int number : intArr)
        {
            System.out.println("Number = " + number);
        }

        // entering the value to be searched
        final int searchVal = 12;

        final int retVal = Arrays.binarySearch(intArr, searchVal);

        System.out.println("The index of element 12 is : " + retVal);
    }
}
