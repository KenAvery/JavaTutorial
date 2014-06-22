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

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class RosterTest
{

    interface CheckPerson
    {
        boolean test(Person p);
    }

    // Approach 1: Create Methods that Search for Persons that Match One
    // Characteristic

    public static void printPersonsOlderThan(final List<Person> roster, final int age)
    {
        for (final Person p : roster)
        {
            if (p.getAge() >= age)
            {
                p.printPerson();
            }
        }
    }

    // Approach 2: Create More Generalized Search Methods

    public static void printPersonsWithinAgeRange(final List<Person> roster, final int low, final int high)
    {
        for (final Person p : roster)
        {
            if (low <= p.getAge() && p.getAge() < high)
            {
                p.printPerson();
            }
        }
    }

    // Approach 3: Specify Search Criteria Code in a Local Class
    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    // Approach 5: Specify Search Criteria Code with a Lambda Expression

    public static void printPersons(final List<Person> roster, final CheckPerson tester)
    {
        for (final Person p : roster)
        {
            if (tester.test(p))
            {
                p.printPerson();
            }
        }
    }

    // Approach 6: Use Standard Functional Interfaces with Lambda Expressions

    public static void printPersonsWithPredicate(final List<Person> roster, final Predicate<Person> tester)
    {
        for (final Person p : roster)
        {
            if (tester.test(p))
            {
                p.printPerson();
            }
        }
    }

    // Approach 7: Use Lambda Expressions Throughout Your Application

    public static void processPersons(
            final List<Person> roster,
            final Predicate<Person> tester,
            final Consumer<Person> block)
    {
        for (final Person p : roster)
        {
            if (tester.test(p))
            {
                block.accept(p);
            }
        }
    }

    // Approach 7, second example

    public static void processPersonsWithFunction(
            final List<Person> roster,
            final Predicate<Person> tester,
            final Function<Person, String> mapper,
            final Consumer<String> block)
    {
        for (final Person p : roster)
        {
            if (tester.test(p))
            {
                final String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    // Approach 8: Use Generics More Extensively

    public static <X, Y> void processElements(
            final Iterable<X> source,
            final Predicate<X> tester,
            final Function<X, Y> mapper,
            final Consumer<Y> block)
    {
        for (final X p : source)
        {
            if (tester.test(p))
            {
                final Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static void main(final String... args)
    {

        final List<Person> roster = Person.createRoster();

        for (final Person p : roster)
        {
            p.printPerson();
        }

        // Approach 1: Create Methods that Search for Persons that Match One
        // Characteristic

        System.out.println("Persons older than 20:");
        printPersonsOlderThan(roster, 20);
        System.out.println();

        // Approach 2: Create More Generalized Search Methods

        System.out.println("Persons between the ages of 14 and 30:");
        printPersonsWithinAgeRange(roster, 14, 30);
        System.out.println();

        // Approach 3: Specify Search Criteria Code in a Local Class

        System.out.println("Persons who are eligible for Selective Service:");

        class CheckPersonEligibleForSelectiveService implements CheckPerson
        {
            @Override
            public boolean test(final Person p)
            {
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        }

        printPersons(roster, new CheckPersonEligibleForSelectiveService());

        System.out.println();

        // Approach 4: Specify Search Criteria Code in an Anonymous Class

        System.out.println("Persons who are eligible for Selective Service " + "(anonymous class):");

        printPersons(roster, new CheckPerson()
        {
            @Override
            public boolean test(final Person p)
            {
                return p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 25;
            }
        });

        System.out.println();

        // Approach 5: Specify Search Criteria Code with a Lambda Expression

        System.out.println("Persons who are eligible for Selective Service " + "(lambda expression):");

        printPersons(roster,
                (final Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25);

        System.out.println();

        // Approach 6: Use Standard Functional Interfaces with Lambda Expressions

        System.out.println("Persons who are eligible for Selective Service " + "(with Predicate parameter):");

        printPersonsWithPredicate(roster,
                p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25);

        System.out.println();

        // Approach 7: Use Lamba Expressions Throughout Your Application

        System.out.println("Persons who are eligible for Selective Service " + "(with Predicate and Consumer parameters):");

        processPersons(roster,
                p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25,
                p -> p.printPerson());

        System.out.println();

        // Approach 7, second example

        System.out.println("Persons who are eligible for Selective Service "
                + "(with Predicate, Function, and Consumer parameters):");

        processPersonsWithFunction(roster,
                p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email));

        System.out.println();

        // Approach 8: Use Generics More Extensively

        System.out.println("Persons who are eligible for Selective Service " + "(generic version):");

        processElements(roster,
                p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25,
                p -> p.getEmailAddress(),
                email -> System.out.println(email));

        System.out.println();

        // Approach 9: Use Bulk Data Operations That Accept Lambda Expressions as Parameters

        System.out.println("Persons who are eligible for Selective Service " + "(with bulk data operations):");

        roster.stream().filter(
                p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 25)
                .map(p -> p.getEmailAddress())
                .forEach(email -> System.out.println(email));
    }
}
