package org.ken.avery.collections.interfaces;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class FunWithQueuesConsumer implements Runnable
{
    private int job = 0;
    private BlockingQueue<String> threadQueue = null;
    private Queue<Integer> queue = null;
    private Semaphore semaphore = null;

    public FunWithQueuesConsumer(
            final int job,
            final BlockingQueue<String> threadQueue,
            final Queue<Integer> queue,
            final Semaphore semaphore)
    {
        super();

        this.job = job;
        this.queue = queue;
        this.threadQueue = threadQueue;
        this.semaphore = semaphore;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " Start  : Job = " + job);
        runJob();
        System.out.println(Thread.currentThread().getName() + " End    : Job = " + job);
    }

    private void runJob()
    {
        Integer queueValue = 0;
        do
        {
            queueValue = getQueueValue();
            System.out.format(Thread.currentThread().getName() + " Working Job %d: Queue Entrie = %d%n", this.job, queueValue);
            doSomeWork();
        }
        while (queueValue != null);

        shutdown();
    }

    private Integer getQueueValue()
    {
        Integer jobValue = 0;

        try
        {
            semaphore.acquire();
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        jobValue = queue.poll();
        semaphore.release();

        return jobValue;
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
            threadQueue.put("SHUTDOWN");
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
