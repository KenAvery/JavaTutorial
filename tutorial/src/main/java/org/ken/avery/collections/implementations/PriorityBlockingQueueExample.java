package org.ken.avery.collections.implementations;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueExample
{
    private static final int JOBS = 5;
    private static final int QUEUE_ENTRIES = 50;
    private static Semaphore semaphore = new Semaphore(1);
    final static BlockingQueue<String> shutdownQueue = new ArrayBlockingQueue<String>(1);
    final static PriorityBlockingQueue<Integer> threadQueue = new PriorityBlockingQueue<Integer>();

    public static void main(final String[] args)
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        loadJobQueue();
        executeJobs(executor);
        waitOnJobs();
        terminateJobs(executor);
    }

    private static void loadJobQueue()
    {
        for (int i = QUEUE_ENTRIES; i > 0; i--)
        {
            threadQueue.add(100 + i);
        }
    }

    private static void executeJobs(final ExecutorService executor)
    {
        for (int i = 0; i < JOBS; i++)
        {
            executor.execute(new PriorityBlockingQueueConsumer((i + 1), semaphore, threadQueue, shutdownQueue));
        }
    }

    private static void waitOnJobs()
    {
        int jobsEnded = 0;
        while (jobsEnded++ < JOBS)
        {
            try
            {
                final String shutdown = shutdownQueue.take();
                System.out.println("Received: " + shutdown);
            }
            catch (final InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void terminateJobs(final ExecutorService executor)
    {
        try
        {
            // waits for termination for 30 seconds only
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }
}
