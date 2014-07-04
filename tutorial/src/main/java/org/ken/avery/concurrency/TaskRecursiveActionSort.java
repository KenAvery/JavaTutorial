package org.ken.avery.concurrency;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

class TaskRecursiveActionSort extends RecursiveAction
{
    private static final long serialVersionUID = 1L;

    // implementation details follow:
    static final int THRESHOLD = 1000;

    final long[] array;
    final int lo, hi;

    TaskRecursiveActionSort(final long[] array, final int lo, final int hi)
    {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    TaskRecursiveActionSort(final long[] array)
    {
        this(array, 0, array.length);
    }

    @Override
    protected void compute()
    {
        System.out.println("Sorting: ");

        for (final long element : array)
        {
            System.out.print(" " + element);
        }
        System.out.println();

        if (hi - lo < THRESHOLD)
        {
            sortSequentially(lo, hi);
        }
        else
        {
            final int mid = (lo + hi) >>> 1;
            invokeAll(new TaskRecursiveActionSort(array, lo, mid), new TaskRecursiveActionSort(array, mid, hi));
            merge(lo, mid, hi);
        }

        System.out.println("Sorted: ");

        for (final long element : array)
        {
            System.out.print(" " + element);
        }
        System.out.println();
    }

    void sortSequentially(final int lo, final int hi)
    {
        Arrays.sort(array, lo, hi);
    }

    void merge(final int lo, final int mid, final int hi)
    {
        final long[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
        {
            array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
        }
    }
}
