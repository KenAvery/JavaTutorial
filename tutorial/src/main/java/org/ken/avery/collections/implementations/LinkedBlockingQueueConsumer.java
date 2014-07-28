package org.ken.avery.collections.implementations;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueConsumer implements Runnable
{
    private int job = 0;
    private BlockingQueue<String> shutdownQueue = null;
    private LinkedBlockingQueue<String> threadQueue = null;

    public LinkedBlockingQueueConsumer(
            final int job,
            final LinkedBlockingQueue<String> threadQueue,
            final BlockingQueue<String> shutdownQueue)
    {
        super();

        this.job = job;
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
        String value = null;
        while ((value = threadQueue.poll()) != null)
        {
            System.out.println("Job " + job + " [" + value + "]");
            doSomeWork();
        }
    }

    private void doSomeWork()
    {
        try
        {
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
            shutdownQueue.put("SHUTDOWN");
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
