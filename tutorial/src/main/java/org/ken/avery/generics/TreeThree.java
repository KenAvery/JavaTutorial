package org.ken.avery.generics;

import java.util.ArrayList;
import java.util.List;

public class TreeThree<V>
{
    // These fields hold the value and the branches
    V value;
    List<TreeThree<? extends V>> branches = new ArrayList<TreeThree<? extends V>>();

    @Override
    public String toString()
    {
        return "TreeThree [value=" + value + ", branches=" + branches + "]";
    }

    // Here's a constructor
    public TreeThree(final V value)
    {
        this.value = value;
    }

    // These are instance methods for manipulating value and branches
    public V getValue()
    {
        return value;
    }

    public void setValue(final V value)
    {
        this.value = value;
    }

    public int getNumBranches()
    {
        return branches.size();
    }

    public TreeThree<? extends V> getBranch(final int n)
    {
        return branches.get(n);
    }

    public void addBranch(final TreeThree<? extends V> branch)
    {
        branches.add(branch);
    }

    /** Recursively compute the sum of the values of all nodes on the tree */
    public static double boundedWildCardSum(final TreeThree<? extends Number> treeThree)
    {
        double total = treeThree.value.doubleValue();

        for (final TreeThree<? extends Number> branch : treeThree.branches)
        {
            total += boundedWildCardSum(branch);
        }

        return total;
    }

    public static <N extends Number> double genaricSum(final TreeThree<N> treeThree)
    {
        final N value = treeThree.value;

        double total = value.doubleValue();

        for (final TreeThree<? extends N> branch : treeThree.branches)
        {
            total += genaricSum(branch);
        }

        return total;
    }

    // This method returns the largest of two trees, where tree size
    // is computed by the sum() method. The type variable ensures that
    // both trees have the same value type and that both can be passed to sum().
    public static <N extends Number> TreeThree<N> max(final TreeThree<N> treeThreeA, final TreeThree<N> treeThreeB)
    {
        final double ts = genaricSum(treeThreeA);
        final double us = genaricSum(treeThreeB);

        if (ts > us)
        {
            return treeThreeA;
        }
        else
        {
            return treeThreeB;
        }
    }

}
