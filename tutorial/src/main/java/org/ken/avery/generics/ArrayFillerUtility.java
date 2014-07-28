package org.ken.avery.generics;

public class ArrayFillerUtility
{
    /**
     * Set all elements of a to the value v; return a.
     */
    public static <T> T[] fill(final T[] array, final T value)
    {
        for (int i = 0; i < array.length; i++)
        {
            array[i] = value;
        }
        return array;
    }

    public static <T> void printFill(final T[] array)
    {
        System.out.print("Fill: ");
        for (final T value : array)
        {
            System.out.print("[" + value + "]");
        }
        System.out.println();
    }
}
