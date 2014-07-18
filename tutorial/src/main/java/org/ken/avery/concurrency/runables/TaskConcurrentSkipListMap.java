package org.ken.avery.concurrency.runables;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Semaphore;

public class TaskConcurrentSkipListMap implements Runnable
{
    private Integer key = null;
    private final String name;
    private static Semaphore semaphore = null;
    private static ConcurrentSkipListMap<Integer, String> concurrentSkipListMap = null;

    TaskConcurrentSkipListMap(
            final Semaphore semaphore,
            final Integer key,
            final String name,
            final ConcurrentSkipListMap<Integer, String> concurrentNavigableMap)
    {
        this.key = key;
        this.name = name;
        TaskConcurrentSkipListMap.semaphore = semaphore;
        TaskConcurrentSkipListMap.concurrentSkipListMap = concurrentNavigableMap;
    }

    @Override
    public void run()
    {
        try
        {
            semaphore.acquire();
            concurrentSkipListMap.put(key, name);
            semaphore.release();
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
