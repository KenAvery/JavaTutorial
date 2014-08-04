package org.ken.avery.collections.implementations.wrappers;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class SynchronizedCollectionConsumer implements Runnable
{
    private int job = 0;
    private Semaphore semaphore = null;
    private BlockingQueue<String> shutdownQueue = null;
    private List<Integer> synchronizedList = null;
    private Collection<Integer> synchronixedCollection = null;

    public SynchronizedCollectionConsumer(
            final int job,
            final Semaphore semaphore,
            final List<Integer> synchronizedList,
            final Collection<Integer> synchronixedCollection,
            final BlockingQueue<String> shutdownQueue)
    {
        super();

        this.job = job;
        this.semaphore = semaphore;
        this.synchronizedList = synchronizedList;
        this.synchronixedCollection = synchronixedCollection;
        this.shutdownQueue = shutdownQueue;
    }

    private void printInitialInformation()
    {
        final Random random = new Random(100);
        try
        {
            semaphore.acquire();

            System.out.println(Thread.currentThread().getName() + " Start  : Job = " + job);
            printCollection("Initial " + job + ":");

            Thread.sleep(random.nextInt(1000));
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
        semaphore.release();
    }

    private void printCollection(final String title)
    {
        System.out.println(title);
        int i = 0;
        for (final Integer value : synchronixedCollection)
        {
            if (i++ >= (synchronixedCollection.size() / 2))
            {
                i = 0;
                System.out.println();
            }
            System.out.print("[" + value + "]");
        }
        System.out.println();
    }

    private void printFinalInformation()
    {
        try
        {
            semaphore.acquire();

            printCollection("Final " + job + ":");
            System.out.println(Thread.currentThread().getName() + " End    : Job = " + job);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
        semaphore.release();
    }

    @Override
    public void run()
    {
        printInitialInformation();

        runJob();

        printFinalInformation();

        shutdown();
    }

    public void runJob()
    {
        for (int i = 0; i < synchronizedList.size(); i++)
        {
            synchronizedList.set(i, (synchronizedList.get(i) + 1));
        }
    }

    private void shutdown()
    {
        try
        {
            shutdownQueue.put("SHUTDOWN Job " + job);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
