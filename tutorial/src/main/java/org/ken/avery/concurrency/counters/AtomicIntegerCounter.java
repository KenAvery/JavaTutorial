package org.ken.avery.concurrency.counters;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter
{
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public void increment()
    {
        atomicInteger.getAndIncrement();
    }

    public void decrement()
    {
        atomicInteger.getAndDecrement();
    }

    public int getCount()
    {
        return atomicInteger.get();
    }
}
