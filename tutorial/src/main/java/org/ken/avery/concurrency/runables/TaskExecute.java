package org.ken.avery.concurrency.runables;

import java.util.concurrent.ForkJoinTask;

public class TaskExecute extends ForkJoinTask<String>
{
    private static final long serialVersionUID = 1L;

    @Override
    protected boolean exec()
    {
        System.out.println("Executing exec method.");
        return true;
    }

    @Override
    public String getRawResult()
    {
        return null;
    }

    @Override
    protected void setRawResult(final String value)
    {
    }
}
