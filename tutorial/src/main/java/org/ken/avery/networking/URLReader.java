package org.ken.avery.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader
{
    public static void main(final String[] args)
            throws Exception
    {
        final URL oracle = new URL("http://www.oracle.com/");
        final BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            System.out.println(inputLine);
        }
        in.close();
    }
}
