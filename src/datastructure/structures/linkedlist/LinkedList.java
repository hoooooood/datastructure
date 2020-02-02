package datastructure.structures.linkedlist;

public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed.Illegal index");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        prev.next = new Node(e, prev.next);
        size++;

    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Get failed.Illegal index");

        Node prev = dummyHead;
        for(int i = 0;i<index;i++)
            prev = prev.next;

        return prev.next.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index,E e){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Set failed.Illegal index");

        Node curr = dummyHead.next;
        for(int i = 0;i<index;i++)
            curr = curr.next;
        curr.e = e;
    }

    public boolean contains(E e){
       Node curr =dummyHead.next;
       while(curr != null){
           if(curr.e.equals(e))
               return true;
           curr = curr.next;
       }
       return false;

    }

    public E remove(int index){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed;Illegal index");
        Node prev = dummyHead;
        for(int i = 0;i < index ; i++)
            prev = prev.next;

        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;
        size--;

        return ret.e;

    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }


    public void removeElement(E e){

        Node prev = dummyHead;
        Node curr = prev.next;

        while(prev != null ){
            if(e == curr.e){
                prev.next = curr.next;
            }else{
                prev = curr;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node curr = dummyHead.next;
        while(curr != null){
            res.append(curr + "->");
            curr = curr.next;
        }

        res.append("null");
        return res.toString();
    }
}
