package org.ken.avery.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class EchoHTTP
{
    public static void main(final String[] args)
            throws Exception
    {
        final String outputString = "{\"string\": \"Echo this!\"}";

        final URL url = new URL("http://httpbin.org/post");
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod("POST");

        final OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(outputString);
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
