package org.ken.avery.concurrency.runables;

public class WorkerThread implements Runnable
{

    private final int job;

    public WorkerThread(final int s)
    {
        this.job = s;
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
    }
}
