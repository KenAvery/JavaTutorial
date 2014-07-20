package org.ken.avery.collections.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class FunWithLists
{
    private static List<String> colorList = new ArrayList<String>(Arrays.asList(
            "Red",
            "Green",
            "Black",
            "White",
            "Black",
            "Gray",
            "Red",
            "White",
            "Blue"));

    private static Set<String> colorSet = new HashSet<String>(Arrays.asList(
            "Aero",
            "Almond",
            "Amber",
            "Beige",
            "White",
            "Blue",
            "Red",
            "Charcoal",
            "Pink",
            "Fallow"));

    public static <E> void swap(final List<E> a, final int i, final int j)
    {
        final E tmp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, tmp);
    }

    public static void shuffle(final List<?> list, final Random rnd)
    {
        for (int i = list.size(); i > 1; i--)
        {
            swap(list, i - 1, rnd.nextInt(i));
        }
    }

    public static <E> void replace(final List<E> list, final E val, final List<? extends E> newVals)
    {
        for (final ListIterator<E> it = list.listIterator(); it.hasNext();)
        {
            if (val == null ? it.next() == null : val.equals(it.next()))
            {
                it.remove();
                for (final E e : newVals)
                {
                    it.add(e);
                }
            }
        }
    }

    public static void main(final String[] args)
    {
        final List<String> combineColorList = new ArrayList<String>(colorList);
        final boolean combineListWithSet = combineColorList.addAll(colorSet);
        final List<String> java8ColorList = colorSet.stream().collect(Collectors.toList());

        System.out.println("Color List                                          : " + colorList);
        System.out.println("Color Set                                           : " + colorSet);
        System.out.println("Combine Color List and Color Set              (" + combineListWithSet + "): " + combineColorList);
        System.out.println("Copy Color Set using Java 8 I/O Stream              : " + java8ColorList);

        Collections.shuffle(combineColorList, new Random());
        System.out.println("Shuffel combine color list using collections shuffle: " + combineColorList);

        // Fun with arrays and lists
        final String[] colorStrings = combineColorList.toArray(new String[combineColorList.size()]);
        System.out.println("Combine color list as a string array                : " + Arrays.toString(colorStrings));

        final List<String> colorStringList = Arrays.asList(colorStrings);
        shuffle(colorStringList, new Random());
        System.out.println("Shuffel combine color list with this classes shuffle: " + colorStringList);

        final StringBuilder list = new StringBuilder("Print color list in reverse order                   : [");
        for (final ListIterator<String> listIterator = colorList.listIterator(colorList.size()); listIterator.hasPrevious();)
        {
            list.append(listIterator.previous() + " (" + listIterator.nextIndex() + ")" + ", ");
        }
        System.out.println(list.replace(list.lastIndexOf(", "), list.lastIndexOf(", ") + 1, "]"));

        replace(java8ColorList, "Red", colorList);
        System.out.println("Relplace \"Red\" in Java 8 Color List with Color List : " + java8ColorList);
    }
}
