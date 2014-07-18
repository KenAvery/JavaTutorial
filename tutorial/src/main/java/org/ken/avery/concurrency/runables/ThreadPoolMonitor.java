package org.ken.avery.concurrency.runables;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolMonitor implements Runnable
{
    private final ThreadPoolExecutor executor;
    private final int interval;
    private BlockingQueue<String> queue = null;

    public ThreadPoolMonitor(final ThreadPoolExecutor executor, final int seconds, final BlockingQueue<String> queue)
    {
        this.executor = executor;
        this.interval = seconds;
        this.queue = queue;
    }

    @Override
    public void run()
    {
        while (true)
        {

            System.out.format("Thread Pool Monitor: Pool/Core [%d/%d], Active/Completed Tasks [%d/%d], Total Tasks: %d%n",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount());

            if (this.executor.getCompletedTaskCount() == this.executor.getTaskCount())
            {
                try
                {
                    queue.put("SHUTDOWN");
                }
                catch (final InterruptedException e)
                {
                    e.printStackTrace();
                }

                break;
            }

            try
            {
                Thread.sleep(interval * 1000);
            }
            catch (final InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
