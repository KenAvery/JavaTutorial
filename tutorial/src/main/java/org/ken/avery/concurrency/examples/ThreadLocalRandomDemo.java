package org.ken.avery.concurrency.examples;

import java.util.concurrent.ForkJoinPool;

import org.ken.avery.concurrency.runables.TaskThreadLocalRandom;

public class ThreadLocalRandomDemo
{
    public static void main(final String[] args)
    {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();

        final TaskThreadLocalRandom taskThreadLocalRandomOne = new TaskThreadLocalRandom("Task one");
        final TaskThreadLocalRandom taskThreadLocalRandomTwo = new TaskThreadLocalRandom("Task two");

        forkJoinPool.invoke(taskThreadLocalRandomOne);
        forkJoinPool.invoke(taskThreadLocalRandomTwo);
    }
}
