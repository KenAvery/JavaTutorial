package org.ken.avery.concurrency.runables;

import java.util.concurrent.RecursiveAction;

public class TaskIncrementRecursiveAction extends RecursiveAction
{
    private static final long serialVersionUID = 1L;

    // implementation details follow:
    static final int THRESHOLD = 1000;

    final long[] array;
    final int lo, hi;

    TaskIncrementRecursiveAction(final long[] array, final int lo, final int hi)
    {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    protected void compute()
    {
        System.out.println("Increment Longs: " + "Low - " + lo + " High - " + hi);

        for (final long element : array)
        {
            System.out.print(" " + element);
        }
        System.out.println();

        if (hi - lo < THRESHOLD)
        {
            for (int i = lo; i <= hi; ++i)
            {
                array[i]++;
            }
        }
        else
        {
            // This code has a bug - needs to be fixed
            final int mid = (lo + hi) >>> 1;
            invokeAll(new TaskIncrementRecursiveAction(array, lo, mid), new TaskIncrementRecursiveAction(array, mid, hi));
        }

        System.out.println("Incramented Longs: ");

        final int end = (array.length < hi) ? array.length : hi;
        for (int i = lo; i <= end; i++)
        {
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }
}
