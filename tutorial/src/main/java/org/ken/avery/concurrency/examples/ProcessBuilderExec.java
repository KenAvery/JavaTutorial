package org.ken.avery.concurrency.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessBuilderExec
{
    public static void main(final String args[])
            throws InterruptedException, IOException
    {
        final ProcessBuilder builder = new ProcessBuilder("ls", "-la");

        final Process process = builder.start();
        final InputStream inputStream = process.getInputStream();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String lineRead;
        while ((lineRead = bufferedReader.readLine()) != null)
        {
            System.out.println(lineRead);
        }

        System.out.println("Process Completed");
    }
}
