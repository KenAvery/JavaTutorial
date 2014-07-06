package org.ken.avery.concurrency;

import java.util.NavigableSet;
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

        System.out.println("Mapping = " + concurrentNavigableMap);

        final NavigableSet<Integer> navigableSet = concurrentNavigableMap.keySet();
        System.out.println("Set of keys = " + navigableSet);

        System.out.println("Size of map = " + concurrentNavigableMap.size());

    }
}
