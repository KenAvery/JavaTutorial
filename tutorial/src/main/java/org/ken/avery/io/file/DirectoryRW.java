package org.ken.avery.io.file;

/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
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

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryRW
{
    static Path tmpTutorial = Paths.get("C:", "tmp", "Java", "Tutorial");

    public static void listRootDirectories()
    {
        final Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        System.out.println("Root Directories:");
        for (final Path name : dirs)
        {
            System.err.println("\t" + name);
        }
    }

    public static void createJavaTutorialTmpDirectory()
    {
        try
        {
            Files.createDirectories(tmpTutorial);
        }
        catch (final IOException e)
        {
            System.out.println("Create Java Tutorial Tmp Directory Failed: " + e.getMessage());
        }
    }

    public static void listDirectoryContents(final Path directory)
    {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory))
        {
            System.out.println("List Directory" + directory);
            for (final Path file : stream)
            {
                System.out.println("\t" + file.getFileName());
            }
        }
        catch (IOException | DirectoryIteratorException x)
        {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }

    public static void listDirectoryFiltered()
    {
        final Path relativeDirectory = Paths.get("src", "main", "java", "org", "ken", "avery", "io", "file");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(relativeDirectory, "*.{java,class,jar}"))
        {
            System.out.println("List Directory Filtered: " + relativeDirectory);
            for (final Path entry : stream)
            {
                System.out.println("\t" + entry.getFileName());
            }
        }
        catch (final IOException x)
        {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
        }
    }

    public static void customListFilter()
    {
        final Path dir = Paths.get("C:", "tmp");

        final DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>()
        {
            @Override
            public boolean accept(final Path file)
                    throws IOException
            {
                return (Files.isDirectory(file));
            }
        };

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, filter))
        {
            for (final Path entry : stream)
            {
                System.out.println(entry.getFileName());
            }
        }
        catch (final IOException x)
        {
            System.err.println(x);
        }
    }

    public static void main(final String[] args)
    {
        final Path directory = Paths.get("C:", "tmp");

        listRootDirectories();
        createJavaTutorialTmpDirectory();
        listDirectoryContents(directory);
        listDirectoryFiltered();
        customListFilter();
    }
}
