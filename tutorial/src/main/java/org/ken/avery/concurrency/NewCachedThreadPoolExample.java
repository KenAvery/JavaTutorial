package org.ken.avery.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NewCachedThreadPoolExample
{
    final static BlockingQueue<String> threadQueue = new ArrayBlockingQueue<String>(1);

    public static void main(final String... args)
            throws InterruptedException, ExecutionException
    {

        //creates cached thread pool
        final ExecutorService executorService = Executors.newCachedThreadPool();

        // runnable thread start to execute.
        executorService.execute(new WorkerThreadQueued(1, threadQueue));

        //callable thread starts to execute
        final Future<String> futureTask = executorService.submit(new CallableTask("Task 2", 3));

        boolean taskComplete = false;
        while (!taskComplete)
        {
            try
            {
                if (!taskComplete && futureTask.isDone())
                {
                    System.out.println("Future Task return value = " + futureTask.get());
                    taskComplete = true;
                }
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        // When the completed tasks = total tasks
        // the thread pool monitor sends SHUTDOWN
        System.out.println("Received: " + threadQueue.take());

        // waits for termination for 30 seconds only
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        executorService.shutdownNow();
    }
}
