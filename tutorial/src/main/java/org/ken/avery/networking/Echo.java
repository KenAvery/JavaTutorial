package org.ken.avery.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Echo
{
    public static void main(final String[] args)
            throws Exception
    {
        final String outputString = "Echo this string";

        final URL url = new URL("http://httpbin.org/post");
        final URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        final OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write("string=" + outputString);
        out.close();

        final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null)
        {
            System.out.println(decodedString);
        }
        in.close();
    }
}
