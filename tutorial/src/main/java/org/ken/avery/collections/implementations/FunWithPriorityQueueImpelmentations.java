package org.ken.avery.collections.implementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import org.ken.avery.collections.interfaces.UndefetedRaceHorses;

public class FunWithPriorityQueueImpelmentations
{
    final static PriorityQueue<String> horseQueue = new PriorityQueue<String>();
    final static PriorityQueue<Integer> colorQueue = new PriorityQueue<Integer>();

    public static void main(final String[] args)
    {
        final List<Integer> integer = new ArrayList<Integer>();
        for (int i = 15; i > 0; i--)
        {
            integer.add(i);
        }
        colorQueue.addAll(integer);

        final Iterator<Integer> colorIterator = colorQueue.iterator();
        while (colorIterator.hasNext())
        {
            System.out.print("[" + colorIterator.next() + "]");
        }
        System.out.println();

        Integer value = 0;
        while ((value = colorQueue.poll()) != null)
        {
            System.out.print("[" + value + "]");
        }
        System.out.println();

        horseQueue.addAll(UndefetedRaceHorses.raceHorses().keySet());
        final Iterator<String> horseIterator = horseQueue.iterator();
        while (horseIterator.hasNext())
        {
            System.out.print("[" + horseIterator.next() + "]");
        }
        System.out.println();

        String horse = null;
        while ((horse = horseQueue.poll()) != null)
        {
            System.out.print("[" + horse + "]");
        }
        System.out.println();
    }
}
