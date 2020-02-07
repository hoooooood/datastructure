package datastructure.structures.tree;

public class RedBlackTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = right = null;
            color = RED;
        }

    }

    private Node root;
    private int size;

    public RedBlackTree(){
        root = null;
        size = 0;
    }

    private boolean isRed(Node node){
        if(node == null)
            return BLACK;
        return node.color;
    }

    /**
     * 左旋转
     * @param node
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 向红黑树添加元素
     * @param key,value
     */
    public void add(K key, V value){
        root = add(root,key,value);
        root.color = BLACK;
    }

    /**
     * 向以node为根的红黑树添加元素，并返回根。
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

    /**
     * 查找最大元素的递归方法
     * @param root
     * @return 节点
     */
    public Node maximum(Node root){
        if(root.right == null)
            return root;
        return maximum(root.right);
    }

    /**
     * 判断传入节点的右子树是否为空
     * 如果空则此节点为整个树最右的节点也就是最大值节点
     * 此时需要移除该节点，需要将左节点来代替传入的节点
     * 如果传入节点右子树不为空，则删除以右子树为根的树的最大值节点
     * 最后返回传入节点，作为删除了最大值节点的树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root,key);
        if(node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    /**
     * 根据传入的节点的值和需要删除的元素做比较
     * 如果当前节点值为空则直接返回
     * 如果当前节点值小于删除元素，则删除该节点左子树里的这个值，并返回左子树的根
     * 如果当前节点值大于删除元素，则删除该节点右子树里的这个值，并返回右子树的根
     * 如果当前节点值等于删除元素，从当前节点左子树中找到先驱（也可以从右子树找后继，但本算法不用）
     * 将找到的先驱替代当前节点，并删除当前的节点
     *
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node,K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            return node;
        } else if (key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            return node;
        }else {
            if(node.left == null){
                Node nodeRight = node.right;
                node.right = null;
                return nodeRight;
            }

            if(node.right == null){
                Node nodeLeft = node.left;
                node.left = null;
                return nodeLeft;
            }

            Node pioneer = maximum(node.left);
            pioneer.left = removeMax(node.left);
            pioneer.right = node.right;
            node.left = node.right = null;
            return pioneer;
        }

    }


    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    public V get(K key) {
        Node node = getNode(root,key);
        return node == null?null:node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node == null)
            throw new IllegalArgumentException("No such node was found!");
        node.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
