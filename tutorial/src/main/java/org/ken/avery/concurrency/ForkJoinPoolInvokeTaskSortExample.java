package org.ken.avery.concurrency;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolInvokeTaskSortExample
{
    static long[] sortLongs = new long[] {
            10L, 0L, 5L, 3L, 8L, 9L, 2L, 1L, 7L, 4L, 6L
    };

    public static void main(final String[] args)
    {
        final ForkJoinPool pool = new ForkJoinPool();
        final TaskRecursiveActionSort sortTask = new TaskRecursiveActionSort(sortLongs);
        final TaskIncrementRecursiveAction incrementTask = new TaskIncrementRecursiveAction(sortLongs, 2, 9);

        pool.invoke(sortTask);
        pool.invoke(incrementTask);

        System.out.println("Active Thread Count: " + pool.getActiveThreadCount());
    }
}
