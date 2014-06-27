package org.ken.avery.inheritance.object;

public class TryCatchFinallyDemo
{
    public TryCatchFinallyDemo()
    {
        System.out.println("TryCatchFinalizeDemo constructor\n");
    }

    private void happyPath()
    {
        try
        {
            System.out.println("Try Happy Path");
        }
        catch (final NullPointerException e)
        {
            System.out.println("Caught in happy path");
        }
        finally
        {
            System.out.println("Finally happy path\n");
        }
    }

    private void throwsException()
    {
        try
        {
            System.out.println("Try throws exception");
            throw new NullPointerException();
        }
        catch (final NullPointerException e)
        {
            System.out.println("Caught in throws exception");
        }
        finally
        {
            System.out.println("Finally throws exception");
        }
    }

    public static void main(final String[] args)
    {
        final TryCatchFinallyDemo demo = new TryCatchFinallyDemo();

        demo.happyPath();
        demo.throwsException();
    }
}
