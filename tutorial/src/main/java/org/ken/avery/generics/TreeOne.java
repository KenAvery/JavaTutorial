package org.ken.avery.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * A tree is a data structure that holds values of type V.
 * Each tree has a single value of type V and can have any number of
 * branches, each of which is itself a Tree.
 */
public class TreeOne<V>
{

    // The value of the tree is of type V.
    V value;

    // A Tree<V> can have branches, each of which is also a Tree<V>
    List<TreeOne<V>> branches = new ArrayList<TreeOne<V>>();

    // Here's the constructor.  Note the use of the type variable V.
    public TreeOne(final V value)
    {
        this.value = value;
    }

    // These are instance methods for manipulating the node value and branches.
    // Note the use of the type variable V in the arguments or return types.
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

    public TreeOne<V> getBranch(final int n)
    {
        return branches.get(n);
    }

    public void addBranch(final TreeOne<V> branch)
    {
        branches.add(branch);
    }
}
