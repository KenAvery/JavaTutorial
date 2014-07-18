package org.ken.avery.collections;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FunWithQueues
{
    private static final int JOBS = 5;
    private static final int PERMITS = 1;
    private static final int QUEUE_ENTRIES = 50;

    final static BlockingQueue<String> threadQueue = new ArrayBlockingQueue<String>(1);
    private static Queue<Integer> queue = new LinkedList<Integer>();
    private static Semaphore semaphore = new Semaphore(PERMITS);

    public static void main(final String[] args)
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        loadJobQueue();
        executeJobs(executor);
        semaphore.release();
        waitOnJobs();
        terminateJobs(executor);
    }

    private static void loadJobQueue()
    {
        try
        {
            semaphore.acquire();
            for (int i = 0; i < QUEUE_ENTRIES; i++)
            {
                queue.add(i);
            }
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private static void executeJobs(final ExecutorService executor)
    {
        for (int i = 0; i < JOBS; i++)
        {
            executor.execute(new FunWithQueuesConsumer((i + 1), threadQueue, queue, semaphore));
        }
    }

    private static void waitOnJobs()
    {
        int jobsEnded = 0;
        while (jobsEnded++ < JOBS)
        {
            try
            {
                final String shutdown = threadQueue.take();
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
