package org.ken.avery.concurrency.examples;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.ken.avery.concurrency.runables.CallableTask;

public class FutureCallableTaskExample
{

    public static void main(final String[] args)
    {
        final CallableTask callable1 = new CallableTask("Task 1", 2);
        final CallableTask callable2 = new CallableTask("Task 2", 1);

        final FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
        final FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

        final ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        boolean taskComplete1 = false;
        boolean taskComplete2 = false;
        while (!(taskComplete1 && taskComplete2))
        {
            try
            {
                if (!taskComplete1 && futureTask1.isDone())
                {
                    System.out.println("Future Task 1 return value = " + futureTask1.get());
                    taskComplete1 = true;
                }

                if (!taskComplete2 && futureTask2.isDone())
                {
                    System.out.println("Future Task 2 return value = " + futureTask2.get());
                    taskComplete2 = true;
                }
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("Done");
        executor.shutdown();
    }
}
