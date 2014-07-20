package org.ken.avery.collections.interfaces;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class FunWithDeque
{
    private static Deque<String> colorDeque = new ArrayDeque<String>(Arrays.asList(
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

    private static List<String> colorList = new LinkedList<String>(Arrays.asList(
            "Red",
            "Green",
            "Black",
            "White",
            "Black",
            "Gray",
            "Red",
            "White",
            "Blue"));

    public static void main(final String[] args)
    {
        mergeLists();
        addColorsFirst();
        addColorsLast();
        removeColors(colorList);
        reverseOrder();
    }

    /**
     * Merge colorDeque and colorList
     */
    public static void mergeLists()
    {
        Collections.addAll(colorDeque, colorList.toArray(new String[0]));
        System.out.println("Color Deque: " + colorDeque);
    }

    /**
     * Remove all from colorDeque and add colors from Colors class FIFO
     */
    public static void addColorsFirst()
    {
        colorDeque.removeAll(colorDeque);
        final Iterator<Entry<Integer, String>> iterator = Colors.colors().entrySet().iterator();
        while (iterator.hasNext())
        {
            final Entry<Integer, String> color = iterator.next();
            if (!colorDeque.contains(color.getValue()))
            {
                colorDeque.addFirst(color.getValue());
            }
        }
        System.out.println("Color Deque: " + colorDeque);
    }

    /**
     * Remove all from colorDeque and add colors from Colors class LIFO
     */
    public static void addColorsLast()
    {

        colorDeque.removeAll(colorDeque);
        final Iterator<Entry<Integer, String>> iterator = Colors.colors().entrySet().iterator();
        while (iterator.hasNext())
        {
            final Entry<Integer, String> color = iterator.next();
            if (!colorDeque.contains(color.getValue()))
            {
                colorDeque.addLast(color.getValue());
            }
        }
        System.out.println("Color Deque: " + colorDeque);
    }

    /**
     * Remove the colors in colorList from colorDeque
     * 
     * @param colorList
     */
    private static void removeColors(final List<String> colorList)
    {
        for (int i = 0; i < colorDeque.size(); i++)
        {
            final Iterator<String> iterator = colorList.iterator();
            while (iterator.hasNext())
            {
                final String color = iterator.next();
                if (colorDeque.contains(color))
                {
                    colorDeque.remove(color);
                }

            }
        }
        System.out.println("Color Deque: " + colorDeque);
    }

    /**
     * Reverse the order of colorDeque
     */
    private static void reverseOrder()
    {
        final Deque<String> deque = new ArrayDeque<String>();
        for (int i = 0; i < colorDeque.size(); i++)
        {
            final String firstColor = colorDeque.peekFirst();
            colorDeque.removeFirst();
            colorDeque.addLast(firstColor);
            deque.addFirst(firstColor);
        }
        colorDeque.removeAll(colorDeque);
        colorDeque.addAll(deque);
        System.out.println("Color Deque: " + colorDeque);
    }
}
