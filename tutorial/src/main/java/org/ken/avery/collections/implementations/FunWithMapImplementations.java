package org.ken.avery.collections.implementations;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class FunWithMapImplementations
{
    public enum COLOR
    {
        RED,
        AERO,
        ALMOND,
        AMBER,
        BEIGE,
        WHITE,
        BLUE,
        CHARCOAL,
        PINK,
        FALLOW,
        GREEN,
        BLACK,
        GRAY,
        AZURE,
    }

    /**
     * If you need SortedMap operations or key-ordered Collection-view
     * iteration, use TreeMap; if you want maximum speed and don't care about
     * iteration order, use HashMap; if you want near-HashMap performance and
     * insertion-order iteration, use LinkedHashMap.
     *
     * @return
     */
    public static void main(final String[] args)
    {

    }

    /**
     *
     * @return
     */
    public static <K, V> Map<K, V> funWithHashMaps()
    {
        final Map<K, V> hashMap = new HashMap<K, V>();

        return hashMap;
    }

    public static <K, V> SortedMap<K, V> funWithTreeMap()
    {
        final SortedMap<K, V> treeMap = new TreeMap<K, V>();

        return treeMap;
    }

    /**
     *
     * @return
     */
    public static <K, V> Map<K, V> funWithLinkedHashMap()
    {
        final Map<K, V> linkedHashMap = new LinkedHashMap<K, V>();

        return linkedHashMap;
    }

    /**
     * EnumMap, which is internally implemented as an array, is a
     * high-performance Map implementation for use with enum keys. This
     * implementation combines the richness and safety of the Map interface with
     * a speed approaching that of an array. If you want to map an enum to a
     * value, you should always use an EnumMap in preference to an array.
     *
     * @return
     */
    public static EnumMap<COLOR, String> funWithEnumMaps()
    {
        final EnumMap<COLOR, String> enumMap = new EnumMap<COLOR, String>(COLOR.class);

        enumMap.put(COLOR.RED, "Red");
        enumMap.put(COLOR.AERO, "Aero");
        enumMap.put(COLOR.ALMOND, "Almond");
        enumMap.put(COLOR.AMBER, "Amber");
        enumMap.put(COLOR.BEIGE, "Beige");
        enumMap.put(COLOR.WHITE, "White");
        enumMap.put(COLOR.BLUE, "Blue");
        enumMap.put(COLOR.CHARCOAL, "Charcoal");
        enumMap.put(COLOR.PINK, "Pink");
        enumMap.put(COLOR.FALLOW, "Fallow");
        enumMap.put(COLOR.GREEN, "Green");
        enumMap.put(COLOR.BLACK, "Black");
        enumMap.put(COLOR.GRAY, "Gray");
        enumMap.put(COLOR.AZURE, "Azure");

        return enumMap;
    }
}
