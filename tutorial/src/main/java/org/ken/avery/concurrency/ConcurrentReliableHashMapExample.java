package org.ken.avery.concurrency;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ConcurrentReliableHashMapExample
{
    private static Semaphore semaphore = new Semaphore(1);
    private static Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<Integer, String>();

    public static void main(final String[] args)
    {
        final ExecutorService executor = Executors.newCachedThreadPool();

        for (Integer key = 0; key < 5; key++)
        {
            executor.execute(new TaskReliableConcurrentHashMap(semaphore, key, "School", concurrentHashMap));
            executor.execute(new TaskReliableConcurrentHashMap(semaphore, key, "Teacher", concurrentHashMap));
            executor.execute(new TaskReliableConcurrentHashMap(semaphore, key, "Student", concurrentHashMap));
            executor.execute(new TaskReliableConcurrentHashMap(semaphore, key, "Father", concurrentHashMap));
            executor.execute(new TaskReliableConcurrentHashMap(semaphore, key, "Mother", concurrentHashMap));
            executor.execute(new TaskReliableConcurrentHashMap(semaphore, key, "Sister", concurrentHashMap));
            executor.execute(new TaskReliableConcurrentHashMap(semaphore, key, "Brother", concurrentHashMap));
        }

        executor.shutdown();

        for (final Entry<Integer, String> entry : concurrentHashMap.entrySet())
        {
            System.out.println("Key " + entry.getKey() + " Value " + entry.getValue());
        }

    }
}
