package org.ken.avery.collections.implementations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FunWithListImplementations
{
    public static void main(final String[] args)
    {

    }

    /**
     * Most of the time, you'll probably use ArrayList, which offers
     * constant-time positional access and is just plain fast. It does not have
     * to allocate a node object for each element in the List, and it can take
     * advantage of System.arraycopy when it has to move multiple elements at
     * the same time. Think of ArrayList as Vector without the synchronization
     * overhead.
     *
     * Note: ArrayList has one tuning parameter — the initial capacity, which
     * refers to the number of elements the ArrayList can hold before it has to
     * grow.
     *
     * @return
     */
    public static <T> List<T> funWithArrayLists()
    {
        final List<T> arrayList = new ArrayList<T>();

        return arrayList;
    }

    /**
     * If you frequently add elements to the beginning of the List or iterate
     * over the List to delete elements from its interior, you should consider
     * using LinkedList. These operations require constant-time in a LinkedList
     * and linear-time in an ArrayList. But you pay a big price in performance.
     * Positional access requires linear-time in a LinkedList and constant-time
     * in an ArrayList. Furthermore, the constant factor for LinkedList is much
     * worse. If you think you want to use a LinkedList, measure the performance
     * of your application with both LinkedList and ArrayList before making your
     * choice; ArrayList is usually faster.
     *
     * Note: LinkedList has no tuning parameters and seven optional operations,
     * one of which is clone. The other six are addFirst, getFirst, removeFirst,
     * addLast, getLast, and removeLast. LinkedList also implements the Queue
     * interface.
     *
     * @return
     */
    public static <T> List<T> funWithLinkedLists()
    {
        final List<T> linkedList = new LinkedList<T>();

        return linkedList;
    }

    /**
     * Each time the List is written to a copy of the entire List is made, this
     * can be expensive for a large List. The usefulness is for Lists that
     * require few modifications and need to be thread safe. When iterating, a
     * snapshot is made of the List, any changes made after the snapshot is
     * made are not reflected in the List.
     * Note: The iterator will never throw ConcurrentModificationException
     *
     * @return
     */
    public static <T> CopyOnWriteArrayList<T> funWithCopyOnWriteArrayList()
    {
        final CopyOnWriteArrayList<T> copyOnWriteArrayList = new CopyOnWriteArrayList<T>();

        return copyOnWriteArrayList;
    }
}
