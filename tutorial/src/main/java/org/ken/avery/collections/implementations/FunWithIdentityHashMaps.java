package org.ken.avery.collections.implementations;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.ken.avery.collections.interfaces.UndefetedRaceHorses;

public class FunWithIdentityHashMaps
{
    /**
     * IdentityHashMap is an identity-based Map implementation based on a hash
     * table. This class is useful for topology-preserving object graph
     * transformations, such as serialization or deep-copying. To perform such
     * transformations, you need to maintain an identity-based "node table" that
     * keeps track of which objects have already been seen. Identity-based maps
     * are also used to maintain object-to-meta-information mappings in dynamic
     * debuggers and similar systems. Finally, identity-based maps are useful in
     * thwarting "spoof attacks" that are a result of intentionally perverse
     * equals methods because IdentityHashMap never invokes the equals method on
     * its keys. An added benefit of this implementation is that it is fast.
     * 
     * @param args
     */
    public static void main(final String args[])
    {
        final IdentityHashMap<String, Integer> identityHashMap = new IdentityHashMap<String, Integer>(
                UndefetedRaceHorses.raceHorses());

        final Iterator<Entry<String, Integer>> iterator = identityHashMap.entrySet().iterator();
        while (iterator.hasNext())
        {
            final String key = iterator.next().getKey();
            if (key == "Kincsem")
            {
                System.out.println("Found: " + key);
            }
        }
    }
}
