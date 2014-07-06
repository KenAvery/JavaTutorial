package org.ken.avery.platform;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesWrite
{
    final static String defaultProperties = "default.properties";
    final static String applicationProperties = "application.properties";

    public static void writePropertiesFile(final String fileName, final Properties properties)
    {
        OutputStream out = null;

        try
        {
            out = new FileOutputStream(fileName);

            // save properties to project root folder
            properties.store(out, null);

        }
        catch (final IOException io)
        {
            io.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (final IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(final String[] args)
    {
        final Properties properties = new Properties();

        // set the default properties value
        properties.setProperty("application", "default");
        properties.setProperty("name", "user");
        properties.setProperty("password", "password");

        writePropertiesFile(defaultProperties, properties);

        properties.clear();

        // set the application properties value
        properties.setProperty("application", "Properties Example");
        properties.setProperty("location", "default");
        properties.setProperty("path", "default");

        writePropertiesFile(applicationProperties, properties);

    }
}
