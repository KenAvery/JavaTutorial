package org.ken.avery.concurrency.runables;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler
{

    @Override
    public void rejectedExecution(final Runnable runnable, final ThreadPoolExecutor executor)
    {
        System.out.println(" Rejecting: " + runnable.toString());
    }

}
