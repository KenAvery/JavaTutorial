package org.ken.avery.generics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionGenericParameter
{
    public static void main(final String[] args)
    {
        final IExceptionGenericParameter<IOException> save = new IExceptionGenericParameter<IOException>()
        {
            @Override
            public void doit(final String filename)
                    throws IOException
            {
                final PrintWriter out = new PrintWriter(new FileWriter(filename));
                out.println(" hello world");
                out.close();
            }
        };

        try
        {
            save.doit("/tmp/foo");
        }
        catch (final IOException e)
        {
            System.out.println(e);
        }
    }
}
