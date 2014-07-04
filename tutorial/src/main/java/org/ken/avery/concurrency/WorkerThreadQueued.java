package org.ken.avery.concurrency;

import java.util.concurrent.BlockingQueue;

public class WorkerThreadQueued implements Runnable
{
    private final int job;
    private BlockingQueue<String> queue = null;

    public WorkerThreadQueued(final int job, final BlockingQueue<String> queue)
    {
        this.job = job;
        this.queue = queue;
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
        try
        {
            System.out.println(Thread.currentThread().getName() + " Working: Job = " + this.job);
            Thread.sleep(10000);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        try
        {
            queue.put("SHUTDOWN");
        }
        catch (final InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
