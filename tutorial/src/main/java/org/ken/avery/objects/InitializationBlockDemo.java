package org.ken.avery.objects;

class InitializationBlockDemo
{

    int[] val = new int[10];

    {
        System.out.println("Running initialization block.");

        for (int i = 0; i < val.length; i++)
        {
            val[i] = (int) (100.0 * Math.random());
        }
    }

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
        InitializationBlockDemo staticBlock = new InitializationBlockDemo();
        staticBlock.values();

        //Create a new instance of the class
        //Notice that the values do not remain the same!
        staticBlock = new InitializationBlockDemo();
        staticBlock.values();
    }
}
