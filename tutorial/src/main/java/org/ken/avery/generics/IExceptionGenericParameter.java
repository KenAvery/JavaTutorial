package org.ken.avery.generics;

public interface IExceptionGenericParameter<X extends Exception>
{
    public void doit(String arg)
            throws X;
}
