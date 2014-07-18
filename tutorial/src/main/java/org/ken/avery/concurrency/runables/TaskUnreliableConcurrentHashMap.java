package org.ken.avery.concurrency.runables;

import java.util.Map;

public class TaskUnreliableConcurrentHashMap implements Runnable
{
    private Integer key = null;
    private final String threadname;
    private static Map<Integer, String> concurrentHashMap = null;

    public TaskUnreliableConcurrentHashMap(
            final Integer key,
            final String threadname,
            final Map<Integer, String> concurrentHashMap)
    {
        this.key = key;
        this.threadname = threadname;
        TaskUnreliableConcurrentHashMap.concurrentHashMap = concurrentHashMap;
    }

    @Override
    public void run()
    {
        while (concurrentHashMap.containsKey(key))
        {
            key += 1;
        }
        final String previousValue = concurrentHashMap.put(key, threadname + " no semaphore: " + key.toString());

        if (previousValue != null)
        {
            System.out.println(threadname + " replaced " + previousValue);
        }
    }
}
