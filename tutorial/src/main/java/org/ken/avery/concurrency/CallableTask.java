package org.ken.avery.concurrency;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String>
{
    private final String name;
    private final long waitTime;

    public CallableTask(final String taskName, final int seconds)
    {
        this.name = taskName;
        this.waitTime = (seconds * 1000);
    }

    @Override
    public String call()
            throws Exception
    {
        Thread.sleep(waitTime);
        return (Thread.currentThread().getName() + ": Task Name - " + this.name);
    }

}
