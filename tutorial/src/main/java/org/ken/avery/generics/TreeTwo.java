package org.ken.avery.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeTwo<V extends Serializable & Comparable<V>> implements Serializable, Comparable<TreeTwo<V>>
{
    // javac -Xlint warns us if we omit this field in a Serializable class
    private static final long serialVersionUID = 833546143621133467L;

    V value;
    List<TreeTwo<V>> branches = new ArrayList<TreeTwo<V>>();

    public TreeTwo(final V value)
    {
        this.value = value;
    }

    // Instance methods
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

    public TreeTwo<V> getBranch(final int n)
    {
        return branches.get(n);
    }

    public void addBranch(final TreeTwo<V> branch)
    {
        branches.add(branch);
    }

    // This method is a nonrecursive implementation of Comparable<Tree<V>>
    // It only compares the value of this node and ignores branches.
    @Override
    public int compareTo(final TreeTwo<V> that)
    {
        if (this.value == null && that.value == null)
        {
            return 0;
        }

        if (this.value == null)
        {
            return -1;
        }

        if (that.value == null)
        {
            return 1;
        }

        return this.value.compareTo(that.value);
    }

}
