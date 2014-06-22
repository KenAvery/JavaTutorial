package org.ken.avery.nestedclasses.lambda;

/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class MethodReferencesTest
{

    // The method transferElements copies elements from one collection to another

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(
            final SOURCE sourceCollection,
            final Supplier<DEST> collectionFactory)
    {

        final DEST result = collectionFactory.get();
        for (final T t : sourceCollection)
        {
            result.add(t);
        }
        return result;
    }

    public static void main(final String... args)
    {

        final List<Person> roster = Person.createRoster();

        for (final Person p : roster)
        {
            p.printPerson();
        }

        final Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

        class PersonAgeComparator implements Comparator<Person>
        {
            @Override
            public int compare(final Person a, final Person b)
            {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }

        // Without method reference
        Arrays.sort(rosterAsArray, new PersonAgeComparator());

        // With lambda expression
        Arrays.sort(rosterAsArray,
                (final Person a, final Person b) -> {
                    return a.getBirthday().compareTo(b.getBirthday());
                }
                );

        // With method reference
        Arrays.sort(rosterAsArray, Person::compareByAge);

        // Reference to an instance method of a particular object
        class ComparisonProvider
        {
            public int compareByName(final Person a,
                    final Person b)
            {
                return a.getName().compareTo(b.getName());
            }

            public int compareByAge(final Person a, final Person b)
            {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        }
        final ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);

        // Reference to an instance method
        // of an arbitrary object of a particular type

        final String[] stringArray = {
                "Barbara", "James", "Mary", "John",
                "Patricia", "Robert", "Michael", "Linda"
        };
        Arrays.sort(stringArray, String::compareToIgnoreCase);

        final Set<Person> rosterSetLambda = transferElements(roster, () -> {
            return new HashSet<>();
        });

        final Set<Person> rosterSet = transferElements(roster, HashSet::new);
        System.out.println("Printing rosterSet:");
        rosterSet.stream().forEach(p -> p.printPerson());
    }
}
