package org.rack4java.utils;

import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class EmptyIterator<T> extends AbstractIterator<T> {
    public static final EmptyIterator it = new EmptyIterator();

    public boolean hasNext() {
        return false;
    }

    public T next() {
        throw new NoSuchElementException();
    }

    public static EmptyIterator it() {
        return it;
    }
}
