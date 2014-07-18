package org.ken.avery.concurrency.runables;

import java.util.concurrent.RecursiveAction;

public class TaskRecursiveAction extends RecursiveAction
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void compute()
    {
        System.out.println("Inside Compute method");
    }
}
