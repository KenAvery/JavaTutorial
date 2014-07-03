package org.ken.avery.concurrency;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolMonitor implements Runnable
{
    private final ThreadPoolExecutor executor;
    private final int interval;

    public ThreadPoolMonitor(final ThreadPoolExecutor executor, final int seconds)
    {
        this.executor = executor;
        this.interval = seconds;
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

            if (this.executor.isShutdown() && this.executor.isTerminated())
            {
                System.out.println("Executor terminated");
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
