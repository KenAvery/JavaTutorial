package org.ken.avery.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinPoolSubmitExample
{
    public static void main(final String[] args)
            throws InterruptedException, ExecutionException
    {
        final ForkJoinPool pool = new ForkJoinPool();
        final TaskSubmit task = new TaskSubmit();

        final ForkJoinTask<String> output = pool.submit(task);

        System.out.println(output.get());
    }
}
