package org.ken.avery.generics;

public class ArrayFillerUtilityDemo
{
    public static void main(final String[] args)
    {
        final Boolean[] booleans = ArrayFillerUtility.fill(new Boolean[10], Boolean.TRUE);
        final Object numberObjects = ArrayFillerUtility.fill(new Number[5], new Integer(42));
        final Integer[] integerArray = ArrayFillerUtility.fill(new Integer[10], 10);

        ArrayFillerUtility.printFill(booleans);
        //        ArrayFillerUtility.printFill(numberObjects); - compiler error!
        ArrayFillerUtility.printFill(integerArray);
    }

}
