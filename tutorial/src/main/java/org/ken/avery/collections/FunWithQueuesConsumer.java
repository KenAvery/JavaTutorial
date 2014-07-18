package org.ken.avery.collections;

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
            try
            {
                semaphore.acquire();
                queueValue = queue.poll();
                System.out.format(Thread.currentThread().getName() + " Working Job %d: Queue Entrie = %d%n", this.job, queueValue);
                Thread.sleep(1000);
                semaphore.release();
            }
            catch (final InterruptedException e1)
            {
                e1.printStackTrace();
            }
        }
        while (queueValue != null);

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
