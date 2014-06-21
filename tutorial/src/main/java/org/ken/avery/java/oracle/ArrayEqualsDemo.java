package org.ken.avery.java.oracle;

import java.util.Arrays;

public class ArrayEqualsDemo
{
    public static void main(final String[] args)
    {

        // initiliazing three object arrays
        final Object[] arr1 = new Object[] {
                1, 123
        };
        final Object[] arr2 = new Object[] {
                1, 123, 22, 4
        };
        final Object[] arr3 = new Object[] {
                1, 123
        };

        // comparing arr1 and arr2
        final boolean retval = Arrays.equals(arr1, arr2);
        System.out.println("arr1 and arr2 equal: " + retval);

        // comparing arr1 and arr3
        final boolean retval2 = Arrays.equals(arr1, arr3);
        System.out.println("arr1 and arr3 equal: " + retval2);
    }
}
