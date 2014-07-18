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
    private static final int PERMITS = 10;
    private static final int QUEUE_ENTRIES = 100;

    final static BlockingQueue<String> threadQueue = new ArrayBlockingQueue<String>(1);
    private static Queue<Integer> queue = new LinkedList<Integer>();
    private static Semaphore semaphore = new Semaphore(PERMITS);

    public static void main(final String[] args)
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        try
        {
            semaphore.acquire();
            for (int i = 0; i < QUEUE_ENTRIES; i++)
            {
                queue.add(i);
            }
            semaphore.release();
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        executor.execute(new FunWithQueuesConsumer(1, threadQueue, queue, semaphore));
        executor.execute(new FunWithQueuesConsumer(2, threadQueue, queue, semaphore));
        executor.execute(new FunWithQueuesConsumer(3, threadQueue, queue, semaphore));
        executor.execute(new FunWithQueuesConsumer(4, threadQueue, queue, semaphore));
        executor.execute(new FunWithQueuesConsumer(5, threadQueue, queue, semaphore));

        int jobsEnded = 0;
        while (jobsEnded++ < 5)
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

        // waits for termination for 30 seconds only
        try
        {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        executor.shutdownNow();
    }
}
