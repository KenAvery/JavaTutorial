package org.ken.avery.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FactoryMethods
{
    public FactoryMethods()
    {

    }

    public static <T, V> Collection<T> mapKeysToCollection(final Class<T> c, final Map<T, V> map)
    {
        final Collection<T> result = new ArrayList<T>();

        final Iterator<Entry<T, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            final T item = iterator.next().getKey();
            result.add(item);
        }

        return result;
    }

    public static <T, V> Collection<V> mapValuesToCollection(final Class<V> c, final Map<T, V> map)
    {
        final Collection<V> result = new ArrayList<V>();

        final Iterator<Entry<T, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            final V item = iterator.next().getValue();
            result.add(item);
        }

        return result;
    }

    public static double sumCollection(final Collection<? extends Number> collection)
    {
        double total = 0.0;
        for (final Number number : collection)
        {
            total += number.doubleValue();
        }
        return total;
    }

    public static <T> void printCollection(final String title, final Collection<T> collection)
    {
        System.out.print(title + ": ");
        for (final T item : collection)
        {
            System.out.print("[" + item + "]");
        }
        System.out.println();
    }
}
