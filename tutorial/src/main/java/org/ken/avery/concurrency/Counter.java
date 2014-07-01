package org.ken.avery.concurrency;

public class Counter
{
    private int i = 0;
    private final int sleepTime = (int) (Math.random() * 10);

    public void iterate()
    {
        for (int j = 0; j < 100; j++)
        {
            i = inc(i);
            try
            {
                Thread.sleep(sleepTime);
            }
            catch (final InterruptedException e)
            {
                System.out.println("Error during sleep");
            }
        }
    }

    public int get()
    {
        return i;
    }

    private int inc(final int n)
    {
        return n + 1;
    }
}
