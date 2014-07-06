package org.ken.avery.concurrency;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConcurrentNavigationMapExample
{
    private static Semaphore semaphore = new Semaphore(1);
    private static ConcurrentNavigableMap<Integer, String> concurrentNavigableMap = new ConcurrentSkipListMap<Integer, String>();

    public static void main(final String args[])
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(new TaskConcurrentNavigationMap(semaphore, 1, "Kincsem", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 2, "Black Caviar", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 3, "Peppers Pride", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 4, "Eclipse", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 5, "Karayel", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 6, "Ormonde", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 7, "Prestige", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 8, "Ribot", concurrentNavigableMap));
        executor.execute(new TaskConcurrentNavigationMap(semaphore, 9, "Colin", concurrentNavigableMap));

        executor.shutdown();
        try
        {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.printf("Size of map    : %s%n", concurrentNavigableMap.size());
        System.out.printf("Set of keys    : %s%n", concurrentNavigableMap.keySet());
        System.out.printf("Descending Set : %s%n", concurrentNavigableMap.descendingKeySet());
        System.out.printf("Floor Entry (5): %s%n", concurrentNavigableMap.floorEntry(5));
        System.out.printf("First Entry    : %s%n", concurrentNavigableMap.firstEntry());
        System.out.printf("Last Key       : %s%n", concurrentNavigableMap.lastKey());
        System.out.printf("First Key      : %s%n", concurrentNavigableMap.firstKey());
        System.out.printf("Original Map   : %s%n", concurrentNavigableMap);
        System.out.printf("Reverse Map    : %s%n", concurrentNavigableMap.descendingMap());
    }
}
