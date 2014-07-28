package org.ken.avery.generics;

import java.util.Collection;

public class Sink implements ISink<Object>
{
    public Sink()
    {
    }

    @Override
    public void flush(final Object t)
    {
        System.out.println("Flushing Object: " + t);
    }

    public static <T> T writeAll(final Collection<T> collection, final ISink<? super T> sink)
    {
        T last = null;
        for (final T type : collection)
        {
            last = type;
            sink.flush(last);
        }
        return last;
    }

}
