package org.ken.avery.collections.implementations;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

import org.ken.avery.collections.interfaces.Colors;

public class FunWithSetImplementations
{
    public static void main(final String[] args)
    {

    }

    /**
     * HashSet is much faster than TreeSet
     * but offers no ordering guarantees.
     * Default initial load factor (size = 16)
     * Iteration time is based on capacity
     */
    public static <T> Set<T> funWithHashSets()
    {
        final Set<T> hashSet = new HashSet<T>();

        return hashSet;
    }

    /**
     * LinkedHashSet is nearly as fast as HashSet
     * and ordering is insertion-oriented.
     * Default initial load factor (size = 16)
     * Iteration time is not effected by capacity
     */
    public static <T> Set<T> funWithLinkedHashSets()
    {
        final Set<T> linkedHhashSet = new LinkedHashSet<T>();

        return linkedHhashSet;
    }

    /**
     * If you need to use the operations in the SortedSet interface, or if
     * value-ordered iteration is required, use TreeSet; otherwise, use HashSet.
     */
    public static <T> SortedSet<T> funWithTreeSets()
    {
        final SortedSet<T> treeSet = new TreeSet<T>();

        return treeSet;
    }

    /**
     * EnumSet is a high-performance Set implementation for enum types. All of
     * the members of an enum set must be of the same enum type. Internally, it
     * is represented by a bit-vector, typically a single long. Enum sets
     * support iteration over ranges of enum types.
     *
     * @return
     */
    public static EnumSet<Colors.Color> funWithEnumSets()
    {
        final EnumSet<Colors.Color> enumSet = EnumSet.range(Colors.Color.AERO, Colors.Color.AZURE);

        return enumSet;
    }

    /**
     * Each time the Set is written to a copy of the entire Set is made, this
     * can be expensive for a large Set. The usefulness is for Sets that require
     * few modifications and need to be thread safe. When iterating, a snapshot
     * is made of the Set, any changes made after the snapshot is made are not
     * reflected in the Set.
     * Note: The iterator will never throw ConcurrentModificationException
     *
     * @return
     */
    public static <T> CopyOnWriteArraySet<T> funWithCopyOnWriteArraySet()
    {
        final CopyOnWriteArraySet<T> copyOnWriteArraySet = new CopyOnWriteArraySet<T>();

        return copyOnWriteArraySet;
    }
}
