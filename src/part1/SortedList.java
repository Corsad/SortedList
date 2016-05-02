package part1;

/**
 * @author Denis Rinfret implemented by Dang Kim Khanh S3372771
 */

public interface SortedList<T extends Comparable> extends List<T> {
    public void add(T element);
}