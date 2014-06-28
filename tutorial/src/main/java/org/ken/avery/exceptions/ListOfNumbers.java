package org.ken.avery.exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

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
public class ListOfNumbers
{
    private final String fileName = "OutFile.txt";
    private final Vector<Integer> vector;
    private static final int SIZE = 10;

    public ListOfNumbers()
    {
        vector = new Vector<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++)
        {
            vector.addElement(new Integer(i));
        }
    }

    public void writeList()
    {
        PrintWriter out = null;

        try
        {
            System.out.println("Entering writer try statement");
            out = new PrintWriter(new FileWriter(fileName));

            for (int i = 0; i < SIZE; i++)
            {
                out.println("Value at: " + i + " = " + vector.elementAt(i));
            }
        }
        catch (final ArrayIndexOutOfBoundsException e)
        {
            System.err.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
        catch (final IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        finally
        {
            if (out != null)
            {
                System.out.println("Closing PrintWriter");
                out.close();
            }
            else
            {
                System.out.println("PrintWriter not open");
            }
        }
    }

    public void readList()
    {

        // Using Try with resource - finally is not needed to close the file
        // BufferedReader in Java 7 implements java.lang.AutoClosable
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;

            System.out.println("Entering reader try statement");

            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (final IOException e)
        {
            System.err.println("Caught IOException opening BufferedReader: " + e.getMessage());
        }

    }

    public void deleteFile()
            throws IOException
    {
        // Throw the IOException and let the caller further up the stack handle it
        final Path path = Paths.get(fileName);
        System.out.format("Deleting file %s:", path);
        Files.delete(path);

    }

    public static void main(final String[] args)
    {
        final ListOfNumbers listOfNumbers = new ListOfNumbers();

        listOfNumbers.writeList();
        listOfNumbers.readList();
        try
        {
            listOfNumbers.deleteFile();
        }
        catch (final IOException e)
        {
            System.err.println("Caught IOException deleteing the file: " + e.getMessage());
        }
    }
}
