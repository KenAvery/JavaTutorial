package org.ken.avery.collections.implementations;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest
{
    public static void main(final String[] args)
    {
        final Map<String, String> hashMap = new HashMap<String, String>();

        final Map<String, String> weakHashMap = new WeakHashMap<String, String>();

        String keyHashMap = new String("keyHashMap");
        String keyWeakHashMap = new String("keyWeakHashMap");

        hashMap.put(keyHashMap, "\"Hash Map Value\"");
        weakHashMap.put(keyWeakHashMap, "\"Weak Hash Map Value\"");

        System.gc();

        System.out.format(
                "Before Garbage Collection:%n\tHashMap value    : %s%n\tWeakHashMap value: %s%n%n",
                hashMap.get("keyHashMap"),
                weakHashMap.get("keyWeakHashMap"));

        keyHashMap = null;
        keyWeakHashMap = null;

        System.gc();

        System.out.format(
                "After  Garbage Collection:%n\tHashMap value    : %s%n\tWeakHashMap value: %s%n%n",
                hashMap.get("keyHashMap"),
                weakHashMap.get("keyWeakHashMap"));
    }
}
