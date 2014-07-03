package org.ken.avery.concurrency;

public class UnpredictableCounter extends Thread
{

    UnpredictableCount counter;

    public static void main(final String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            final UnpredictableCount counter = new UnpredictableCount();
            final UnpredictableCounter counterThread1 = new UnpredictableCounter(counter);
            final UnpredictableCounter counterThread2 = new UnpredictableCounter(counter);

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

    public UnpredictableCounter(final UnpredictableCount counter)
    {
        this.counter = counter;
    }

    @Override
    public void run()
    {
        counter.iterate();
    }
}
