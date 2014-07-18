package org.ken.avery.concurrency.runables;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.Semaphore;

public class TaskConcurrentNavigationMap implements Runnable
{
    private Integer key = null;
    private final String name;
    private static Semaphore semaphore = null;
    private static ConcurrentNavigableMap<Integer, String> concurrentNavigableMap = null;

    public TaskConcurrentNavigationMap(
            final Semaphore semaphore,
            final Integer key,
            final String name,
            final ConcurrentNavigableMap<Integer, String> concurrentNavigableMap)
    {
        this.key = key;
        this.name = name;
        TaskConcurrentNavigationMap.semaphore = semaphore;
        TaskConcurrentNavigationMap.concurrentNavigableMap = concurrentNavigableMap;
    }

    @Override
    public void run()
    {
        try
        {
            semaphore.acquire();
            concurrentNavigableMap.put(key, name);
            semaphore.release();
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
