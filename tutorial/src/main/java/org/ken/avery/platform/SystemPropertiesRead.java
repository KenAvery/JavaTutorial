package org.ken.avery.platform;

public class SystemPropertiesRead
{
    public static void main(final String[] args)
    {
        System.out.println("File Separator : " + System.getProperty("file.separator"));
        System.out.println("Class Path     : " + System.getProperty("java.class.path"));
        System.out.println("Java Home      : " + System.getProperty("java.home"));
        System.out.println("Java Vendor    : " + System.getProperty("java.vendor"));
        System.out.println("Java Vendor URL: " + System.getProperty("java.vendor.url"));
        System.out.println("Java Version   : " + System.getProperty("java.version"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));
        System.out.println("OS Name        : " + System.getProperty("os.name"));
        System.out.println("OS Version     : " + System.getProperty("os.version"));
        System.out.println("Path Separator : " + System.getProperty("path.separator"));
        System.out.println("User Directory : " + System.getProperty("user.dir"));
        System.out.println("User Home      : " + System.getProperty("user.home"));
        System.out.println("User Name      : " + System.getProperty("user.name"));
    }
}
