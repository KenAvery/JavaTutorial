package org.ken.avery.concurrency.examples.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.ken.avery.concurrency.runables.WorkerThread;

public class SimpleThreadPool
{
    private static final int THREADS = 5;
    private static final int JOBS = 10;

    public static void main(final String[] args)
    {
        final ExecutorService threadPool = Executors.newFixedThreadPool(THREADS);

        for (int job = 0; job < JOBS; job++)
        {
            final Runnable worker = new WorkerThread(job + 1);
            threadPool.execute(worker);
        }

        threadPool.shutdown();

        while (!threadPool.isTerminated())
        {
        }

        System.out.format("%nFinished running %d jobs on %d worker threads%n", JOBS, THREADS);
    }

}
