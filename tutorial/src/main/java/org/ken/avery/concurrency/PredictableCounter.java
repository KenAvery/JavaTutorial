package org.ken.avery.concurrency;

public class PredictableCounter extends Thread
{
    SynchronizedCounter counter;

    public static void main(final String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            final SynchronizedCounter counter = new SynchronizedCounter();
            final PredictableCounter counterThread1 = new PredictableCounter(counter);
            final PredictableCounter counterThread2 = new PredictableCounter(counter);

            counterThread1.start();
            counterThread2.start();

            try
            {
                counterThread1.join();
                counterThread2.join();
            }
            catch (final InterruptedException e)
            {
                System.out.println("Error during join");
            }

            System.out.println("The variable i= " + counter.get() + ", the expected value i= 200");
        }

    }

    public PredictableCounter(final SynchronizedCounter counter)
    {
        this.counter = counter;
    }

    @Override
    public void run()
    {
        counter.iterate();
    }
}
