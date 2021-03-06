package org.ken.avery.concurrency.examples.runnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.ken.avery.concurrency.runables.RejectedExecutionHandlerImpl;
import org.ken.avery.concurrency.runables.ThreadPoolMonitor;
import org.ken.avery.concurrency.runables.WorkerThread;

public class WorkerPool
{
    private static final int CORE_THREADS = 2;
    private static final int MAX_THREADS = 4;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNITS = TimeUnit.SECONDS;
    private static final int JOBS = 10;
    private static final int MONITOR_INTERVAL_SECONDS = 3;

    static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);

    public static void main(final String args[])
            throws InterruptedException
    {
        // Rejection Handler
        final RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();

        // ThreadFactory
        final ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // Creating the ThreadPoolExecutor
        final ThreadPoolExecutor executorPool = new ThreadPoolExecutor(
                CORE_THREADS,
                MAX_THREADS,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNITS,
                new ArrayBlockingQueue<Runnable>(CORE_THREADS),
                threadFactory,
                rejectionHandler);

        //start the thread pool monitor
        final ThreadPoolMonitor threadPoolMonitor = new ThreadPoolMonitor(executorPool, MONITOR_INTERVAL_SECONDS, queue);
        final Thread monitorThread = new Thread(threadPoolMonitor);
        monitorThread.start();

        //submit jobs to the thread pool
        for (int i = 0; i < JOBS; i++)
        {
            executorPool.execute(new WorkerThread(i + 1));
        }

        // When the completed tasks = total tasks
        // the thread pool monitor sends SHUTDOWN
        System.out.println("Received: " + queue.take());

        //shut down the pool
        executorPool.shutdown();
    }
}
