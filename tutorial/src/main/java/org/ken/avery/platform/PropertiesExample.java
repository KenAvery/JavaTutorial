package org.ken.avery.platform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesExample
{
    final static String defaultProperties = "default.properties";
    final static String applicationProperties = "application.properties";

    public static Properties loadProperties(final String propertiesFile)
    {
        final Properties properties = new Properties();
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(propertiesFile);
            properties.load(in);
            in.close();
        }
        catch (final FileNotFoundException e2)
        {
            e2.printStackTrace();
        }
        catch (final IOException e1)
        {
            e1.printStackTrace();
        }

        return properties;
    }

    public static void saveProperties(final String propertiesFile, final Properties properties)
    {
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream(propertiesFile);
            properties.store(out, "--- " + propertiesFile + " ---");
            out.close();
        }
        catch (final FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (final IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args)
    {
        final Properties defaultProps = loadProperties(defaultProperties);
        defaultProps.list(System.out);

        String version = null;
        if (defaultProps.containsKey("Version"))
        {
            version = "2.00";
        }
        else
        {
            version = defaultProps.getProperty("Version", "1.00");
        }
        defaultProps.setProperty("Version", version);
        saveProperties(defaultProperties, defaultProps);

        System.out.println();

        final Properties applicationProps = loadProperties(applicationProperties);
        applicationProps.list(System.out);
    }
}
