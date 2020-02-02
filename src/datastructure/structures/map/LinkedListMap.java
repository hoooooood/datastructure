package datastructure.structures.map;

public class LinkedListMap<K,V> implements Map<K,V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null,null);
        }

        public Node() {
            this(null, null,null);
        }

        @Override
        public String toString() {
            return key.toString()+":"+value.toString();
        }
    }

    private Node dummyhead;
    private int size;

    public LinkedListMap(){
        dummyhead = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            dummyhead.next = new Node(key,value,dummyhead.next);
        } else {
            node.value = value;
        }

    }

    @Override
    public V remove(K key) {
        Node  prev = dummyhead;
        while(prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }
        if(prev != null){
            V value = prev.next.value;
            prev.next = prev.next.next;
            size--;
            return value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null:node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node == null)
            throw new IllegalArgumentException("there is no key "+key);

        node.value = newValue;

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key){
        Node curr = dummyhead.next;
        while(curr != null){
            if(curr.key == key)
                return curr;
            curr = curr.next;
        }
        return null;
    }


}
