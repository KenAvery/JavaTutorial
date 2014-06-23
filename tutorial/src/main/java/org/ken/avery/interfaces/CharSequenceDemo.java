package org.ken.avery.interfaces;

/*
 * Copyright (c) 2008,2012 Oracle and/or its affiliates. All rights reserved.
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

// CharSequenceDemo presents a String value -- backwards.
public class CharSequenceDemo implements CharSequence
{
    private final String s;

    public CharSequenceDemo(final String s)
    {
        //It would be much more efficient to just reverse the string
        //in the constructor. But a lot less fun!
        this.s = s;
    }

    //If the string is backwards, the end is the beginning!
    private int fromEnd(final int i)
    {
        return s.length() - 1 - i;
    }

    @Override
    public char charAt(final int i)
    {
        if ((i < 0) || (i >= s.length()))
        {
            throw new StringIndexOutOfBoundsException(i);
        }
        return s.charAt(fromEnd(i));
    }

    @Override
    public int length()
    {
        return s.length();
    }

    @Override
    public CharSequence subSequence(final int start, final int end)
    {
        if (start < 0)
        {
            throw new StringIndexOutOfBoundsException(start);
        }
        if (end > s.length())
        {
            throw new StringIndexOutOfBoundsException(end);
        }
        if (start > end)
        {
            throw new StringIndexOutOfBoundsException(start - end);
        }
        final StringBuilder sub = new StringBuilder(s.subSequence(fromEnd(end), fromEnd(start)));
        return sub.reverse();
    }

    @Override
    public String toString()
    {
        final StringBuilder s = new StringBuilder(this.s);
        return s.reverse().toString();
    }

    //Random int from 0 to max. As random() generates values between 0 and 0.9999
    private static int random(final int max)
    {
        return (int) Math.round(Math.random() * (max + 1));
    }

    public static void main(final String[] args)
    {
        final CharSequenceDemo s = new CharSequenceDemo(
                "Write a class that implements the CharSequence interface found in the java.lang package.");

        //exercise charAt() and length()
        for (int i = 0; i < s.length(); i++)
        {
            System.out.print(s.charAt(i));
        }

        System.out.println("");

        //exercise subSequence() and length();
        final int start = random(s.length() - 1);
        final int end = random(s.length() - 1 - start) + start;
        System.out.println(s.subSequence(start, end));

        //exercise toString();
        System.out.println(s);

    }
}
