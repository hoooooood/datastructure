package datastructure.structures.map;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    private class Node{
        private K key;
        private V value;
        private Node left,right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = right = null;
        }

    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    /**
     * 向二分搜索树添加元素
     * @param key,value
     */
    @Override
    public void add(K key, V value){
        root = add(root,key,value);
    }

    /**
     * 向以node为根的二分搜索树添加元素，并返回根。
     * 如果传入的根为null则新建以e为值的新节点并作为返回值
     * @param node
     * @param key
     * @param value
     * @return
     */
    public Node add(Node node,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }

        if(node.key.compareTo(key) > 0)
            node.left = add(node.left,key,value);
        if(node.key.compareTo(key) < 0)
            node.right = add(node.right,key,value);
        else
            node.value = value;
        return node;
    }

    /**
     * 查找以node为根中键值为Key的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node,K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) == 0)
            return node;
        else if(key.compareTo(node.key) > 0)
            return getNode(node.right,key);
        else
            return getNode(node.left,key);
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void set(K key, V newValue) {

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
