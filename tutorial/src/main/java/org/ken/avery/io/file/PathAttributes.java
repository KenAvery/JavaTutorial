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

import static java.nio.file.FileSystems.getDefault;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;

public class PathAttributes
{
    public static void absolutePath()
    {
        // Microsoft Windows syntax
        final String title = "Absolute Path";
        final Path path = Paths.get(System.getProperty("user.home"), "logs", "foo.log");

        printPathAttributes(path, false, title);
    }

    public static void relativePath()
    {
        // Microsoft Windows syntax
        final String title = "Relative Path";
        final Path path = Paths.get("foo\\bar");

        printPathAttributes(path, true, title);

    }

    public static void defaultPath()
    {
        final String title = "Default Path";
        final FileSystem systemDefault = getDefault();
        final Path defaultPath = systemDefault.getPath("foo", "bar");

        printPathAttributes(defaultPath, true, title);
    }

    public static void pathFromURI()
    {
        final String title = "URI ";
        final Path path = Paths.get(System.getProperty("user.home"), "logs", "foo.log");
        final URI uri = URI.create(path.toUri().toString());

        final String schema = uri.getScheme();
        if (schema == null)
        {
            throw new IllegalArgumentException("Missing scheme");
        }

        //check for default provider to avoid loading of installed providers
        if (schema.equalsIgnoreCase("file"))
        {
            final Path schemaPath = FileSystems.getDefault().provider().getPath(uri).toAbsolutePath();
            printPathAttributes(schemaPath, false, title + schema);
        }

        // try to find provider
        for (final FileSystemProvider provider : FileSystemProvider.installedProviders())
        {
            if (provider.getScheme().equalsIgnoreCase(schema))
            {
                final Path providerPath = provider.getPath(uri).toAbsolutePath();
                printPathAttributes(providerPath, true, title + "Provider");
                break;
            }
        }
    }

    public static void realPath()
    {
        final String title = "Real Path";
        final Path path = Paths.get("xanadu.txt");
        final Path fullPath = path.toAbsolutePath();

        Path realPath = null;
        try
        {
            realPath = fullPath.toRealPath();
        }
        catch (final NoSuchFileException x)
        {
            System.err.format("%s: no such" + " file or directory%n", path);
            // Logic for case when file doesn't exist.
        }
        catch (final IOException x)
        {
            System.err.format("%s%n", x);
            // Logic for other sort of file error.
        }

        printPathAttributes(realPath, false, title);
    }

    public static void currentPath()
    {
        final String title = "Current Path";
        final Path path = Paths.get("");

        printPathAttributes(path, true, title);
    }

    public static void joinPaths()
    {
        final String title = "Join Path";
        final Path path = Paths.get("");

        printPathAttributes(path.resolve("xanadu.txt"), true, title);
    }

    public static void printPathAttributes(final Path path, final boolean relative, final String title)
    {
        System.out.format("%n%s:%n", title);
        System.out.format("Convert %s to URI  : %s%n", title, path.toUri());

        if (relative)
        {
            System.out.format("Convert using to absolute path: %s%n", path.toAbsolutePath().toString());
        }

        System.out.format("\ttoString    : %s%n", path.toString());
        System.out.format("\tgetFileName : %s%n", path.getFileName());
        System.out.format("\tgetName(0)  : %s%n", path.getName(0));
        System.out.format("\tgetNameCount: %d%n", path.getNameCount());
        if (path.getNameCount() >= 2)
        {
            System.out.format("\tsubpath(0,2): %s%n", path.subpath(0, 2));
        }
        System.out.format("\tgetParent   : %s%n", path.getParent());
        System.out.format("\tgetRoot     : %s%n", path.getRoot());
    }

    public static void main(final String[] args)
    {
        absolutePath();
        relativePath();
        defaultPath();
        pathFromURI();
        realPath();
        currentPath();
        joinPaths();
    }
}
