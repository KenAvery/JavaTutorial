package org.ken.avery.concurrency;

import java.util.concurrent.ForkJoinPool;

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
