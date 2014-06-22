package org.ken.avery.objects;

class StaticInitializationMethodDemo
{

    static int[] val = initializeClassVariable();

    void values()
    {
        for (final int element : val)
        {
            System.out.print(" " + element);
        }
        System.out.println();
    }

    public static void main(final String[] args)
    {
        //Instantiate this class
        StaticInitializationMethodDemo initializeBlock = new StaticInitializationMethodDemo();
        initializeBlock.values();

        //Create a new instance of the class
        //Notice that the values remain the same!
        initializeBlock = new StaticInitializationMethodDemo();
        initializeBlock.values();
    }

    private static int[] initializeClassVariable()
    {
        System.out.println("Running initialization block.");

        final int[] val = new int[10];
        for (int i = 0; i < val.length; i++)
        {
            val[i] = (int) (100.0 * Math.random());
        }

        return val;
    }
}
