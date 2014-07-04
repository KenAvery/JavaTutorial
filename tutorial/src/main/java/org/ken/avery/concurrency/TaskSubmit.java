package org.ken.avery.concurrency;

import java.util.concurrent.Callable;

public class TaskSubmit implements Callable<String>
{
    public String call()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (final InterruptedException e)
        {
            System.out.println(e);
        }
        return "Task Completed";
    }

}
