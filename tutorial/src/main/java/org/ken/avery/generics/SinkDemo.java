package org.ken.avery.generics;

import java.util.Collection;

import org.ken.avery.collections.interfaces.UndefetedRaceHorses;

public class SinkDemo
{
    public static void main(final String[] args)
    {
        final ISink<Object> isinkTypeObject = new Sink();
        final Collection<String> collectionStringSet = UndefetedRaceHorses.raceHorses().keySet();
        final String string = Sink.writeAll(collectionStringSet, isinkTypeObject);

        System.out.println("The last flush: " + string);
    }
}
