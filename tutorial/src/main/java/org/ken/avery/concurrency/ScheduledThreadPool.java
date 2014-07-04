package org.ken.avery.concurrency;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool
{
    private static final int EXECUTION_DELAY = 10;
    private static final TimeUnit TIME_UNITS = TimeUnit.SECONDS;
    private static final int THREADS = 3;

    public static void main(final String[] args)
            throws InterruptedException
    {
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        //schedule to run after sometime
        System.out.println("Current Time = " + new Date());
        for (int i = 0; i < THREADS; i++)
        {
            Thread.sleep(1000);
            final WorkerThread workerThread = new WorkerThread(i + 1);
            scheduledThreadPool.schedule(workerThread, EXECUTION_DELAY, TIME_UNITS);
        }

        //add some delay to let some threads spawn by scheduler
        Thread.sleep(30000);

        scheduledThreadPool.shutdown();
        while (!scheduledThreadPool.isTerminated())
        {
            //wait for all tasks to finish
        }
        System.out.println("Finished all threads");
    }

}
