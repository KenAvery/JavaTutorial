package org.ken.avery.exceptions;

public class CustomExceptionDemo
{
    public static void main(final String[] args)
    {
        try
        {
            CustomExceptionTest();
        }
        catch (final CustomException e)
        {
            System.out.println("Caught Custom Exception: " + e.getMessage());
        }

        try
        {
            CustomExceptionTest(null);
        }
        catch (final CustomException e)
        {
            System.out.println("Caught Custom Exception: " + e.toString());
        }

        try
        {
            CustomExceptionThrowsTest();
        }
        catch (final CustomException cause)
        {
            System.out.println("Caught Custom Exception: " + cause.getMessage());
            final StackTraceElement elements[] = cause.getStackTrace();
            for (final StackTraceElement element : elements)
            {
                System.err.println(element.getFileName()
                        + ":" + element.getLineNumber()
                        + ">> "
                        + element.getMethodName() + "()");
            }
        }
    }

    static void CustomExceptionTest()
            throws CustomException
    {
        throw new CustomException();
    }

    static void CustomExceptionTest(final String string)
            throws CustomException
    {
        if (string == null)
        {
            throw new CustomException("String is null");
        }
    }

    static void CustomExceptionThrowsTest()
            throws CustomException
    {
        try
        {
            System.out.println("Try throws exception");
            throw new NullPointerException();
        }
        catch (final NullPointerException e)
        {
            System.out.println("Caught in throws exception");
            throw new CustomException(e);
        }
    }

}
