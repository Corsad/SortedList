package part1;

/*
 * List interface used for teaching linked data structures
 * in the programming 2 course
 * Version 3 of the package
 */

import java.util.Iterator;

/**
 * @author Denis Rinfret implemented by Dang Kim Khanh S3372771
 */
public interface List<T> {
    public void addToHead(T obj);
    public void addToTail(T obj);
    public T getFromHead();
    public T getFromTail();
    public Iterator getIterator();
    public T removeFromHead();
    public T removeFromTail();
}
