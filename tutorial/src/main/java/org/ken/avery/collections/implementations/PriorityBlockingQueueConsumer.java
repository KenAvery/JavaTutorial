package org.ken.avery.collections.implementations;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class PriorityBlockingQueueConsumer implements Runnable
{
    private int job = 0;
    private Semaphore semaphore = null;
    private BlockingQueue<String> shutdownQueue = null;
    private PriorityBlockingQueue<Integer> threadQueue = null;

    public PriorityBlockingQueueConsumer(
            final int job,
            final Semaphore semaphore,
            final PriorityBlockingQueue<Integer> threadQueue,
            final BlockingQueue<String> shutdownQueue)
    {
        super();

        this.job = job;
        this.semaphore = semaphore;
        this.threadQueue = threadQueue;
        this.shutdownQueue = shutdownQueue;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " Start  : Job = " + job);
        runJob();
        System.out.println(Thread.currentThread().getName() + " End    : Job = " + job);
        shutdown();
    }

    public void runJob()
    {
        Integer value = null;
        while ((value = threadQueue.poll()) != null)
        {
            doSomeWork(value);
        }
    }

    private void doSomeWork(final Integer value)
    {
        try
        {
            semaphore.acquire();
            System.out.println("Job " + job + " [" + value + "]");
            semaphore.release();

            Thread.sleep(1000);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
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
