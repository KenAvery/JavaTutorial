package org.ken.avery.concurrency.counters;

import org.ken.avery.concurrency.runables.TaskAtomicIntegerWorker;

public class AtomicIntegerExample
{
    public static void main(final String[] args)
            throws InterruptedException
    {
        final AtomicIntegerCounter automicIntegerCounter = new AtomicIntegerCounter();

        final Thread atomicIntegerOne = new Thread(new TaskAtomicIntegerWorker(automicIntegerCounter, true, 10000));
        atomicIntegerOne.start();

        final Thread atomicIntegerTwo = new Thread(new TaskAtomicIntegerWorker(automicIntegerCounter, false, 10000));
        atomicIntegerTwo.start();

        final Thread atomicIntegerThree = new Thread(new TaskAtomicIntegerWorker(automicIntegerCounter, true, 50));
        atomicIntegerThree.start();

        atomicIntegerOne.join();
        atomicIntegerTwo.join();
        atomicIntegerThree.join();

        System.out.println("Atomic Integer Total = " + automicIntegerCounter.getCount());
    }
}
