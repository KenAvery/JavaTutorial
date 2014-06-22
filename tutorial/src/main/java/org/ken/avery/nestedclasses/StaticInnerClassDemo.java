package org.ken.avery.nestedclasses;

public class StaticInnerClassDemo
{
    public static void main(final String args[])
    {

        final StaticNested nested = new StaticNested();
        nested.name();
    }

    //static nested class in java
    private static class StaticNested
    {
        public void name()
        {
            System.out.println("static nested class example in java");
        }
    }
}
