package org.ken.avery.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NewSingleThreadExecutorExample
{
    public static void main(final String... args)
            throws InterruptedException, ExecutionException
    {
        // creates thread pool with one thead
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        // callable thread starts to execute
        final Future<String> futureTask = executorService.submit(new CallableTask("Task 1", 3));

        boolean taskComplete = false;
        while (!taskComplete)
        {
            try
            {
                if (futureTask.isDone())
                {
                    System.out.println("Future Task 1 return value = " + futureTask.get());
                    taskComplete = true;
                }
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("Done");
        executorService.shutdown();

        // waits for termination for 30 seconds only
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        // checks for thread termination
        final boolean isTerminated = executorService.isTerminated();
        System.out.println(isTerminated);
    }

}
