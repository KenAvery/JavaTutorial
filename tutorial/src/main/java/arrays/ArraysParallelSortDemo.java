package arrays;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ArraysParallelSortDemo
{
    public static void main(final String[] args)
    {
        final int DATA_SIZE = 100000000;

        final int[] data = new int[DATA_SIZE];
        for (int i = 0; i < data.length; i++)
        {
            data[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }

        System.out.println("Data array of 100 Million int");

        LocalDateTime start = LocalDateTime.now();
        Arrays.parallelSort(data);
        System.out.println("Parallel sort finished in: " +
                Duration.between(start, LocalDateTime.now()).toMillis() +
                " Milliseconds");

        start = LocalDateTime.now();
        Arrays.sort(data);
        System.out.println("         Sort finished in: " +
                Duration.between(start, LocalDateTime.now()).toMillis() +
                " Milliseconds");
    }
}
