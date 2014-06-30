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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

public class FileAttributes
{
    public static void setFileModifiedTime(final Path file)
    {
        final long currentTime = System.currentTimeMillis();
        final FileTime fileTime = FileTime.fromMillis(currentTime);

        System.out.println("Setting Modified File Time: " + file);
        System.out.println("\tNew File time: " + fileTime.toString());

        try
        {
            Files.setLastModifiedTime(file, fileTime);
        }
        catch (final IOException e)
        {
            System.out.format("Set Time Stamps Failed: %s%n", e.getMessage());
        }
    }

    public static void printBasicAttributes(final BasicFileAttributes basicAttributes)
    {
        if (basicAttributes != null)
        {
            System.out.println("Basic File Attributes:");
            System.out.println("\tcreationTime    : " + basicAttributes.creationTime());
            System.out.println("\tlastAccessTime  : " + basicAttributes.lastAccessTime());
            System.out.println("\tlastModifiedTime: " + basicAttributes.lastModifiedTime());
            System.out.println("\tisDirectory     : " + basicAttributes.isDirectory());
            System.out.println("\tisOther         : " + basicAttributes.isOther());
            System.out.println("\tisRegularFile   : " + basicAttributes.isRegularFile());
            System.out.println("\tisSymbolicLink  : " + basicAttributes.isSymbolicLink());
            System.out.println("\tsize            : " + basicAttributes.size());
        }

    }

    public static void printDosFileAttributes(final DosFileAttributes dosFileAttributes)
    {
        if (dosFileAttributes != null)
        {
            System.out.println("DOS File Attributes:");
            System.out.println("\tisReadOnly: " + dosFileAttributes.isReadOnly());
            System.out.println("\tisHidden  : " + dosFileAttributes.isHidden());
            System.out.println("\tisArchive : " + dosFileAttributes.isArchive());
            System.out.println("\tisSystem  : " + dosFileAttributes.isSystem());
        }

    }

    public static void printPosixFilePermissions(final PosixFileAttributes posisFileAttributes)
    {
        if (posisFileAttributes != null)
        {
            System.out.println("POSIX File Permissions: ");
            System.out.println("\tOwner      : " + posisFileAttributes.owner().getName());
            System.out.println("\tGroup      : " + posisFileAttributes.group().getName());
            System.out.println("\tPermissions: " + PosixFilePermissions.toString(posisFileAttributes.permissions()));
        }
    }

    public static BasicFileAttributes getBasicFileAttributes(final Path file)
    {
        BasicFileAttributes basicAttributes = null;

        try
        {
            basicAttributes = Files.readAttributes(file, BasicFileAttributes.class);
        }
        catch (final IOException e)
        {
            System.out.format("Get Basic File Attributed Failed: %s%n", e.getMessage());
        }

        return basicAttributes;
    }

    public static DosFileAttributes getDosFileAttributes(final Path file)
    {
        DosFileAttributes dosFileAttributes = null;

        try
        {
            dosFileAttributes = Files.readAttributes(file, DosFileAttributes.class);
        }
        catch (final UnsupportedOperationException x)
        {
            System.err.println("DOS file" + " attributes not supported:" + x);
        }
        catch (final IOException e)
        {
            System.err.println("DOS file IO Exception: " + e.getMessage());
        }

        return dosFileAttributes;
    }

    public static void hideDosfile(final Path file)
    {
        System.out.println("Hidding DOS file: " + file);
        try
        {
            Files.setAttribute(file, "dos:hidden", true);
        }
        catch (final IOException e)
        {
            System.out.format("Failed to hide DOS file: %s%n\tIOException: %s%n", file.toString(), e.getMessage());
        }
    }

    public static void revealDosfile(final Path file)
    {
        System.out.println("Revealing DOS file: " + file);
        try
        {
            Files.setAttribute(file, "dos:hidden", false);
        }
        catch (final IOException e)
        {
            System.out.format("Failed to revealing DOS file: %s%n\tIOException: %s%n", file.toString(), e.getMessage());
        }
    }

    public static PosixFileAttributes getPosixFileAttributes(final Path file)
    {
        PosixFileAttributes posisFileAttributes = null;
        try
        {
            posisFileAttributes = Files.readAttributes(file, PosixFileAttributes.class);
        }
        catch (final UnsupportedOperationException u)
        {
            System.out.format(
                    "Failed to get POSIX File Attributes for: %s%n\tUnsupportedOperationException: %n", file.toString());
        }
        catch (final IOException e)
        {
            System.out.format("Failed to get POSIX File Attributes for: %s%n\tIOException: %s%n", file.toString(), e.getMessage());
        }

        return posisFileAttributes;
    }

    public static void main(final String[] args)
    {
        final Path file = Paths.get("xanadu.txt");
        final BasicFileAttributes basicAttributes = getBasicFileAttributes(file);
        final DosFileAttributes dosFileAttributes = getDosFileAttributes(file);
        final PosixFileAttributes posisFileAttributes = getPosixFileAttributes(file);

        printBasicAttributes(basicAttributes);
        setFileModifiedTime(file);
        hideDosfile(file);
        printDosFileAttributes(dosFileAttributes);
        revealDosfile(file);
        printDosFileAttributes(dosFileAttributes);
        printPosixFilePermissions(posisFileAttributes);
    }

}
