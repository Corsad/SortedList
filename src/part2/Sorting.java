package part2;


import part1.SortedLinkedList;
import java.util.Iterator;
import javax.swing.DefaultListModel;

/**
 * @author S3372771
 */
public class Sorting {

    private SortedLinkedList sorted;
    private String list[];

    public Sorting(String[] list) {
        this.list = list;
    }
    
    public DefaultListModel sortingList(){
        for(int i = 0; i < list.length; i++){
            sorted.add(list[i]);
        }
        return sorted.getName(sorted, list.length);
    }
    
    public int listLength(){
        return list.length;
    }
}
