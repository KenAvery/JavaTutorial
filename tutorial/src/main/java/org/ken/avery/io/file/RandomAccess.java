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

import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RandomAccess
{
    static Path path = Paths.get("Happy to be Alive.txt");
    static String outputString = "I am here again, in a familiar place feeling something%n" +
            " I've felt before, wondering why it's still here, why I didn't deal with it more" +
            " fully before. But I'm glad I have a second chance at it ... and I know that if" +
            " I need a third chance, I'll get it. I also know that if it comes up again, I'll " +
            "recognize it sooner and deal with it more readily. This is growth. And, I am happy to be alive.";

    public static Path createNewFile()
    {
        Path file = null;
        try
        {
            // Create the empty file with default permissions, etc.
            file = Files.createFile(path);
        }
        catch (final FileAlreadyExistsException x)
        {
            System.err.format("file named %s" + " already exists%n", path);
        }
        catch (final IOException x)
        {
            // Some other sort of failure, such as permissions.
            System.err.format("createFile error: %s%n", x);
        }
        return file;
    }

    public static void writeToFile(final Path file, final String string)
    {
        final Charset charset = Charset.forName("US-ASCII");
        if (file != null)
        {
            try (BufferedWriter writer = Files.newBufferedWriter(file, charset))
            {
                writer.write(string, 0, string.length());
            }
            catch (final IOException x)
            {
                System.err.format("IOException: %s%n", x);
            }
        }

    }

    public static void iWasHere()
    {
        final String s = "I was here!\n";
        final byte data[] = s.getBytes();
        final ByteBuffer out = ByteBuffer.wrap(data);

        final ByteBuffer copy = ByteBuffer.allocate(12);

        try (FileChannel fc = (FileChannel.open(path, READ, WRITE)))
        {
            // Read the first 12
            // bytes of the file.
            int nread;
            do
            {
                nread = fc.read(copy);
            }
            while (nread != -1 && copy.hasRemaining());

            // Write "I was here!" at the beginning of the file.
            fc.position(0);
            while (out.hasRemaining())
            {
                fc.write(out);
            }
            out.rewind();

            // Move to the end of the file.  Copy the first 12 bytes to
            // the end of the file.  Then write "I was here!" again.
            final long length = fc.size();
            fc.position(length - 1);
            copy.flip();
            while (copy.hasRemaining())
            {
                fc.write(copy);
            }
            while (out.hasRemaining())
            {
                fc.write(out);
            }
        }
        catch (final IOException x)
        {
            System.out.println("I/O Exception: " + x);
        }
    }

    public static void main(final String[] args)
    {
        final Path file = createNewFile();
        writeToFile(file, outputString);
        iWasHere();
    }
}
