package org.ken.avery.collections.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FunWithSets
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

    public static <E> Set<E> removeDups(final Collection<E> c)
    {
        return new LinkedHashSet<E>(c);
    }

    public static void main(final String[] args)
    {
        final Set<String> colorApi = removeDups(colorList);
        final Collection<String> colorCreate = new LinkedHashSet<String>(colorList);
        final Set<String> colorAggregate = colorList.stream().collect(Collectors.toSet());

        System.out.println("Original List              : " + colorList);
        System.out.println("Remove Dups Create Set     : " + colorCreate);
        System.out.println("Remove Dups JDK 8 Aggregate: " + colorAggregate);
        System.out.println("Remove Dups API            : " + colorApi);

        System.out.println();

        System.out.println("Does \"Remove Dups API\" Contain All of \"Remove Dups Create Set\"?: "
                + colorApi.containsAll(colorCreate));

        System.out.println();

        System.out.println("Color Created Set                                  : " + colorCreate);
        System.out.println("Color Set                                          : " + colorSet);
        final boolean colorSetIntersection = colorCreate.retainAll(colorSet);
        System.out.println("Intersection of Color Create with Color Set  (" + colorSetIntersection + "): " + colorCreate);
        System.out.println();

        System.out.println("Color Aggregate Set                                : " + colorAggregate);
        System.out.println("Color Set                                          : " + colorSet);
        final boolean colorSetAsymmetric = colorAggregate.removeAll(colorSet);
        System.out.println("Complement of Color Aggregate with Color Set (" + colorSetAsymmetric + "): " + colorAggregate);
        System.out.println();

        System.out.println("Color Created Set                                  : " + colorCreate);
        System.out.println("Color Aggregate Set                                : " + colorAggregate);
        final boolean colorSetUnion = colorCreate.addAll(colorAggregate);
        System.out.println("Union of Color Created with Color Aggregate  (" + colorSetUnion + "): " + colorCreate);
        System.out.println();

        System.out.println("Color API Set                                      : " + colorApi);
        System.out.println("Color Set                                          : " + colorSet);
        colorApi.addAll(colorSet);
        colorCreate.retainAll(colorSet);
        colorApi.removeAll(colorCreate);
        final boolean colorSetSymmetricDiff = colorApi.removeAll(colorCreate);
        System.out.println("Symetric Diff of Color API with Color Set   (" + colorSetSymmetricDiff + "): " + colorApi);
        System.out.println();
    }
}
