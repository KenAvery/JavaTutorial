package org.ken.avery.concurrency.examples.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.ken.avery.concurrency.runables.TaskConcurrentSkipListMap;

public class ConcurrentSkipListMapExample
{
    private static Semaphore semaphore = new Semaphore(1);
    private static ConcurrentSkipListMap<Integer, String> concurrentSkipListMap = new ConcurrentSkipListMap<Integer, String>();

    public static void main(final String args[])
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(new TaskConcurrentSkipListMap(semaphore, 1, "Kincsem", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 2, "Black Caviar", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 3, "Peppers Pride", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 4, "Eclipse", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 5, "Karayel", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 6, "Ormonde", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 7, "Prestige", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 8, "Ribot", concurrentSkipListMap));
        executor.execute(new TaskConcurrentSkipListMap(semaphore, 9, "Colin", concurrentSkipListMap));

        executor.shutdown();
        try
        {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.printf("Size of map    : %s%n", concurrentSkipListMap.size());
        System.out.printf("Set of keys    : %s%n", concurrentSkipListMap.keySet());
        System.out.printf("Descending Set : %s%n", concurrentSkipListMap.descendingKeySet());
        System.out.printf("Floor Entry (5): %s%n", concurrentSkipListMap.floorEntry(5));
        System.out.printf("First Entry    : %s%n", concurrentSkipListMap.firstEntry());
        System.out.printf("Last Key       : %s%n", concurrentSkipListMap.lastKey());
        System.out.printf("First Key      : %s%n", concurrentSkipListMap.firstKey());
        System.out.printf("Original Map   : %s%n", concurrentSkipListMap);
        System.out.printf("Reverse Map    : %s%n", concurrentSkipListMap.descendingMap());
    }
}
