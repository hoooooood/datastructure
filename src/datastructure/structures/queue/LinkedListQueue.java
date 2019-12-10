package datastructure.structures.queue;



public class LinkedListQueue<E> implements Queue<E> {

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

    private Node head,tail;
    private int size;


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if(isEmpty())
            head = tail = new Node(e);
        else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("No Element can be dequeued");

        Node ret = head;
        head = head.next;
        ret.next = null;
        size--;
        if(isEmpty())
            tail = null;

        return ret.e;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("The queue is empty!");
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node curr = head;
        while (curr != null){
            res.append(curr.e+"->");
            curr = curr.next;
        }
        res.append("null");
        res.append(" tail");
        return res.toString();
    }
}
