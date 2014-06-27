package org.ken.avery.inheritance.object;

import java.util.GregorianCalendar;

public class CallFinalizeDemo extends GregorianCalendar
{
    private static final long serialVersionUID = 1L;

    public static void main(final String[] args)
    {
        try
        {
            // Create a new CallFinalizeDemo object
            final CallFinalizeDemo calendar = new CallFinalizeDemo();

            // Print the current date time
            System.out.println("" + calendar.getTime());

            // finalize calendar
            System.out.println("Finalizing...");
            calendar.finalize();
            System.out.println("Finalized.");

        }
        catch (final Throwable ex)
        {
            ex.printStackTrace();
        }

    }
}
