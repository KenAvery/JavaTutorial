package org.ken.avery.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

import org.ken.avery.concurrency.runables.TaskRecursiveAction;

public class ForkJoinPoolInvokeExample
{
    public static void main(final String[] args)
    {
        final ForkJoinPool pool = new ForkJoinPool();
        final TaskRecursiveAction task = new TaskRecursiveAction();

        pool.invoke(task);

        System.out.println(pool.getActiveThreadCount());
    }
}
