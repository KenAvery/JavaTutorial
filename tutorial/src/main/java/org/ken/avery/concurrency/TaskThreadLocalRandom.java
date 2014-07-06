package org.ken.avery.concurrency;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;

public class TaskThreadLocalRandom extends ForkJoinTask<String>
{
    private String msg = null;

    public TaskThreadLocalRandom(final String msg)
    {
        this.msg = msg;
    }

    private static final long serialVersionUID = 1L;

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
