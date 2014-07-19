package org.ken.avery.collections;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FunWithMaps
{

    public static void main(final String[] args)
    {
        final Map<String, Integer> frequencyMap = frequencyMaper(Colors.colors());
        final Map<String, Integer> newMap = newAttributeMap(frequencyMap, frequencyMap);

        printMap("Frequency", newMap);
        printMap("Colors", Colors.colors());

        isDuplicateMap(frequencyMap, newMap);
        isMapKeysEqual(newMap, Colors.colors());
    }

    private static Map<String, Integer> frequencyMaper(final Map<Integer, String> map)
    {
        final Map<String, Integer> frequencyMap = new HashMap<String, Integer>();

        final Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            final String key = iterator.next().getValue();
            final Integer frequency = frequencyMap.get(key);
            frequencyMap.put(key, (frequency == null) ? 1 : frequency + 1);
        }

        System.out.println(frequencyMap.size() + " distinct words:");
        System.out.println(frequencyMap);

        return frequencyMap;
    }

    static <K, V> Map<K, V> newAttributeMap(final Map<K, V> defaults, final Map<K, V> overrides)
    {
        final Map<K, V> result = new HashMap<K, V>(defaults);
        result.putAll(overrides);
        return result;
    }

    private static <K, V> void printMap(final String name, final Map<K, V> map)
    {
        System.out.format("%n%s Keys: %n\t", name);
        for (final K key : map.keySet())
        {
            System.out.print("[" + key + "]");
        }

        System.out.format("%n%s Map: %n\t", name);
        for (final Map.Entry<K, V> entry : map.entrySet())
        {
            System.out.print("[" + entry.getKey() + ": " + entry.getValue() + "]");
        }
        System.out.println();
    }

    private static void isDuplicateMap(final Map<String, Integer> mapOne, final Map<String, Integer> mapTwo)
    {
        if (mapOne.entrySet().containsAll(mapTwo.entrySet()))
        {
            System.out.println("Map 1 equals Map 2");
        }
        else
        {
            System.out.println("Map 1 not equal to Map 2");
        }
    }

    private static void isMapKeysEqual(final Map<String, Integer> mapOne, final Map<Integer, String> mapTwo)
    {
        if (mapOne.keySet().equals(mapTwo.keySet()))
        {
            System.out.println("Map 1 keys equal Map 2");
        }
        else
        {
            System.out.println("Map 1 keys not equal to Map 2");
        }
    }
}
