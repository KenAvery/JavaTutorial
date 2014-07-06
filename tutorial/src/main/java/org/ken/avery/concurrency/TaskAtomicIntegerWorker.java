package org.ken.avery.concurrency;

public class TaskAtomicIntegerWorker implements Runnable
{
    private final AtomicIntegerCounter counter;
    private final boolean increment;
    private final int count;

    public TaskAtomicIntegerWorker(final AtomicIntegerCounter counter, final boolean increment, final int count)
    {
        this.counter = counter;
        this.increment = increment;
        this.count = count;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < this.count; i++)
        {
            if (increment)
            {
                this.counter.increment();
            }
            else
            {
                this.counter.decrement();
            }
        }
    }
}
