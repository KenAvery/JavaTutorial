package org.ken.avery.concurrency;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentUnreliableHashMapExample
{
    private static Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<Integer, String>();

    public static void main(final String[] args)
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        for (Integer key = 0; key < 5; key++)
        {
            executor.execute(new TaskUnreliableConcurrentHashMap(key, "School", concurrentHashMap));
            executor.execute(new TaskUnreliableConcurrentHashMap(key, "Teacher", concurrentHashMap));
            executor.execute(new TaskUnreliableConcurrentHashMap(key, "Student", concurrentHashMap));
            executor.execute(new TaskUnreliableConcurrentHashMap(key, "Father", concurrentHashMap));
            executor.execute(new TaskUnreliableConcurrentHashMap(key, "Mother", concurrentHashMap));
            executor.execute(new TaskUnreliableConcurrentHashMap(key, "Sister", concurrentHashMap));
            executor.execute(new TaskUnreliableConcurrentHashMap(key, "Brother", concurrentHashMap));
        }

        executor.shutdown();

        for (final Entry<Integer, String> entry : concurrentHashMap.entrySet())
        {
            System.out.println("Key " + entry.getKey() + " Value " + entry.getValue());
        }

    }
}
