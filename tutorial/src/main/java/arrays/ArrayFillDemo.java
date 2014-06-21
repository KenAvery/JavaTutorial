package arrays;

import java.util.Arrays;

public class ArrayFillDemo
{
    public static void main(final String[] args)
    {

        // initializing int array
        final int arr[] = new int[] {
                1, 6, 3, 2, 9
        };

        // let us print the values
        System.out.println("Actual values: ");
        for (final int value : arr)
        {
            System.out.println("Value = " + value);
        }

        // using fill for placing 18
        Arrays.fill(arr, 18);

        // let us print the values
        System.out.println("New values after using fill() method: ");
        for (final int value : arr)
        {
            System.out.println("Value = " + value);
        }
    }
}
