package org.ken.avery.concurrency.runables;

import java.util.Map;
import java.util.concurrent.Semaphore;

public class TaskReliableConcurrentHashMap implements Runnable
{
    private Integer key = null;
    private final String threadname;
    private static Semaphore semaphore = null;
    private static Map<Integer, String> concurrentHashMap = null;

    TaskReliableConcurrentHashMap(
            final Semaphore semaphore,
            final Integer key,
            final String threadname,
            final Map<Integer, String> concurrentHashMap)
    {
        this.key = key;
        this.threadname = threadname;
        TaskReliableConcurrentHashMap.semaphore = semaphore;
        TaskReliableConcurrentHashMap.concurrentHashMap = concurrentHashMap;
    }

    @Override
    public void run()
    {
        try
        {
            semaphore.acquire();
            while (concurrentHashMap.containsKey(key))
            {
                key += 1;
            }
            concurrentHashMap.put(key, threadname + " semaphore: " + key.toString());
            semaphore.release();
        }
        catch (final InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
