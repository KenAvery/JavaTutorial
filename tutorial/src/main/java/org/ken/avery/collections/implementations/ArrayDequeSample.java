package org.ken.avery.collections.implementations;

/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
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
/**
 *ArrayDequeSample.java creates and prints the contents of an ArrayDeque.
 */
import java.util.ArrayDeque;

public class ArrayDequeSample
{
    public static void main(final String[] args)
    {
        final ArrayDeque<String> aDeque = new ArrayDeque<>();

        aDeque.addFirst("tea");
        aDeque.addFirst("milk");
        aDeque.addFirst("coffee");
        aDeque.addLast("sugar");
        printDeque(aDeque);

        System.out.println("Add Juice first and honey last");

        aDeque.addFirst("juice");
        aDeque.addLast("honey");
        printDeque(aDeque);

        System.out.println("First Element : " + aDeque.getFirst());
        System.out.println("Last Element : " + aDeque.getLast());

        /* Removal of the Deque Elements */
        System.out.println("First Element(Removed):" + aDeque.removeFirst());
        System.out.println("Last Element (Removed):" + aDeque.removeLast());
        printDeque(aDeque);

        System.out.println("Popped Element : " + aDeque.pop());
        System.out.println("Size of Array Deque: " + aDeque.size());
        printDeque(aDeque);
    }

    public static void printDeque(final ArrayDeque<String> aDeque)
    {
        /* Iterate through elements in an ArrayDeque instance */
        System.out.println();
        for (final Object element : aDeque)
        {
            System.out.println(element);
        }
        System.out.println();
    }
}
