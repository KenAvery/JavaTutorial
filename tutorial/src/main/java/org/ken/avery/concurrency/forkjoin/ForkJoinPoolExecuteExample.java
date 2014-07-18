package org.ken.avery.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

import org.ken.avery.concurrency.runables.TaskExecute;

public class ForkJoinPoolExecuteExample
{
    public static void main(final String[] args)
    {
        final ForkJoinPool pool = new ForkJoinPool();
        final TaskExecute task = new TaskExecute();

        pool.execute(task);

        task.join();
    }
}
