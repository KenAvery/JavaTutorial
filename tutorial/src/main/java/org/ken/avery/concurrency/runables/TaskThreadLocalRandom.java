package org.ken.avery.concurrency.runables;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;

public class TaskThreadLocalRandom extends ForkJoinTask<String>
{
    private static final long serialVersionUID = 1L;
    private String msg = null;

    public TaskThreadLocalRandom(final String msg)
    {
        this.msg = msg;
    }

    @Override
    protected boolean exec()
    {
        final int i = ThreadLocalRandom.current().nextInt(1, 10);
        System.out.println("ThreadLocalRandom for " + msg + ": " + i);
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
