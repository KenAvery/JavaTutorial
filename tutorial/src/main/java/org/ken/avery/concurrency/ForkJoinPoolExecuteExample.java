package org.ken.avery.concurrency;

import java.util.concurrent.ForkJoinPool;

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
