package org.ken.avery.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader
{
    public static void main(final String[] args)
            throws Exception
    {
        final URL oracle = new URL("http://www.oracle.com/");
        final URLConnection yc = oracle.openConnection();
        final BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            System.out.println(inputLine);
        }
        in.close();
    }
}
