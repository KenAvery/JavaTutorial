package org.ken.avery.nestedclasses;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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
public class InnerClassDemo
{
    // Create an array
    private final static int SIZE = 15;
    private final int[] arrayOfInts = new int[SIZE];

    public InnerClassDemo()
    {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++)
        {
            arrayOfInts[i] = i;
        }
    }

    public void printEven()
    {

        // Print out values of even indices of the array
        final IntArrayIterator iterator = this.new EvenIterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    interface IntArrayIterator extends java.util.Iterator<Integer>
    {
    }

    // Inner class implements the DataStructureIterator interface,
    // which extends the Iterator<Integer> interface

    private class EvenIterator implements IntArrayIterator
    {

        // Start stepping through the array from the beginning
        private int nextIndex = 0;

        @Override
        public boolean hasNext()
        {
            // Check if the current element is the last in the array
            return (nextIndex <= SIZE - 1);
        }

        @Override
        public Integer next()
        {

            // Record a value of an even index of the array
            final Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }

    public static void main(final String s[])
    {

        // Fill the array with integer values and print out only
        // values of even indices
        final InnerClassDemo ds = new InnerClassDemo();
        ds.printEven();
    }
}
