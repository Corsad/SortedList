package part1;

/**
 * @author Dang Kim Khanh S3372771
 */
public class TestSortedLinkedList {

    public static void main(String[] args) {
        SortedLinkedList<Integer> linkTest = new SortedLinkedList<Integer>();
        System.out.println(linkTest);
        linkTest.add(new Integer(5));
        linkTest.add(new Integer(4));
        linkTest.add(new Integer(6));
        linkTest.add(new Integer(8));
        linkTest.add(new Integer(7));
        linkTest.add(new Integer(3));
        linkTest.add(new Integer(4));
        

        System.out.println(linkTest);
    }
}
