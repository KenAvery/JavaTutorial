package org.ken.avery.collections.implementations.wrappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SynchronizedCollectionExample
{
    private static final int JOBS = 5;
    private static final int LIST_ENTRIES = 25;
    private static final int COLLECTION_ENTRIES = 25;
    private static Semaphore semaphore = new Semaphore(1);
    final static BlockingQueue<String> shutdownQueue = new ArrayBlockingQueue<String>(1);
    static List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
    final static Collection<Integer> synchronixedCollection = Collections.synchronizedCollection(synchronizedList);

    public static void main(final String[] args)
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        loadJobList();
        loadJobCollection();
        printCollection("Initial :");
        executeConsumerJobs(executor);
        waitOnJobs();
        printCollection("Final : ");
        terminateJobs(executor);
    }

    private static void printCollection(final String title)
    {
        System.out.println(title);
        int i = 0;
        synchronized (synchronizedList)
        {
            for (final Integer value : synchronizedList)
            {
                if (i++ >= LIST_ENTRIES)
                {
                    i = 0;
                    System.out.println();
                }
                System.out.print("[" + value + "]");
            }
            System.out.println();
        }

    }

    private static void loadJobList()
    {
        synchronized (synchronizedList)
        {
            for (int i = LIST_ENTRIES; i > 0; i--)
            {
                synchronizedList.add(100 + i);
            }
        }
    }

    private static void loadJobCollection()
    {
        synchronized (synchronixedCollection)
        {
            for (int i = COLLECTION_ENTRIES; i > 0; i--)
            {
                synchronixedCollection.add(200 + i);
            }
        }
    }

    private static void executeConsumerJobs(final ExecutorService executor)
    {
        for (int i = 0; i < JOBS; i++)
        {
            executor.execute(new SynchronizedCollectionConsumer(
                    (i + 1),
                    semaphore,
                    synchronizedList,
                    synchronixedCollection,
                    shutdownQueue));
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

                // Ensure threads do not intermingle console print output
                // Consider this as an example of managing resource access
                semaphore.acquire();
                System.out.println("Received: " + shutdown);
                semaphore.release();
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
