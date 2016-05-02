package part1;


import java.util.Iterator;
import javax.swing.DefaultListModel;

/**
 * @author Denis Rinfret implemented by Dang Kim Khanh S3372771
 */
public class SortedLinkedList<T extends Comparable> implements SortedList<T> {

    private Node head;
    private Node tail;

    @Override
    public void add(T obj) {

        if (head == null) {
            addToHead(obj);
        } else {

            Node cur = head;
            Node temp = new Node(obj, null, null);
            int result = (cur.obj).compareTo((temp.obj));

            if (result == -1 && tail == head) {
                addToTail(obj);
            } else if (result == 1 || result == 0) {
                if (cur == head) {
                    addToHead(obj);
                } else {
                    cur.prev.next = temp;
                    temp.prev = cur.prev;
                    cur.prev = temp;
                    temp.next = cur;
                }
            } else if (result == -1) {
                while (result == -1) {
                    if (cur.next == null) {
                        addToTail(obj);
                        result = 2;
                    } else {
                        cur = cur.next;
                        result = (cur.obj).compareTo((temp.obj));
                    }
                }
                if (result != 2) {
                    cur.prev.next = temp;
                    temp.prev = cur.prev;
                    cur.prev = temp;
                    temp.next = cur;
                }
            }
        }
    }

    public SortedLinkedList() {
        head = null;
        tail = head;
    }

    @Override
    public void addToHead(T obj) {
        head = new Node(obj, null, head);
        if (tail == null) {
            tail = head;
        }
        if (head.next != null) {
            head.next.prev = head;
        }
    }

    @Override
    public void addToTail(T obj) {
        if (head == null) {
            addToHead(obj);
        } else {
            tail = new Node(obj, tail, null);
            tail.prev.next = tail;
        }
    }

    @Override
    public T getFromHead() {
        if (head == null) {
            return null;
        } else {
            return head.obj;
        }
    }

    @Override
    public T getFromTail() {
        if (tail == null) {
            return null;
        } else {
            return tail.obj;
        }
    }

    @Override
    public Iterator getIterator() {
        return new DLLIterator();
    }

    @Override
    public String toString() {

        StringBuilder buf = new StringBuilder("head --> ");
        Iterator iter = this.getIterator();

        while (iter.hasNext()) {
            buf.append(iter.next().toString());

            if (iter.hasNext()) {
                buf.append("<-->");
            }
        }
        buf.append("<-- tail");

        return buf.toString();
    }
    
    public DefaultListModel getName(SortedLinkedList list, int i){
        Iterator iter = this.getIterator();
        DefaultListModel listModel = null;
        int j = 0;
        while (iter.hasNext()) {
            listModel.addElement(iter.next());
        }
        return listModel;
    }

    @Override
    public T removeFromHead() {
        if (head == null) {
            return null;
        } else {
            T ret = head.obj;
            Node old = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            old.next = null;
            return ret;
        }
    }

    @Override
    public T removeFromTail() {
        if (tail == null) {
            return null;
        } else {
            T ret = tail.obj;
            Node old = tail;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            old.prev = null;
            return ret;


        }
    }

    class Node {

        private T obj;
        private Node next;
        private Node prev;

        Node(T obj) {
            this.obj = obj;
            next = null;
            prev = null;
        }

        Node(T obj, Node prev, Node next) {
            this.obj = obj;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + obj + "]";
        }
    }

    class DLLIterator implements Iterator {

        Node current;

        DLLIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            if (current != null) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                return null;
            } else {
                Node temp = current;
                current = current.next;
                return temp.obj;
            }
        }

        @Override
        public void remove() {
            if (current != null) {
                Node temp = current;
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {

                    head = current.next;
                }
                if (current.next != null) {

                    current.next.prev = current.prev;
                } else {

                    tail = current.prev;
                }
                current = current.next;
                temp.next = null;
                temp.prev = null;
            }
        }
    }
}
