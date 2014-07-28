package org.ken.avery.generics;

interface ISink<T>
{
    void flush(T t);
}
