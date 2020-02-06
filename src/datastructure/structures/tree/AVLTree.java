package datastructure.structures.tree;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
            height = 1;
        }

    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 向二分搜索树添加元素
     *
     * @param key,value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的二分搜索树添加元素，并返回根。
     * 如果传入的根为null则新建以e为值的新节点并作为返回值
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    public Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) > 0)
            node.left = add(node.left, key, value);
        if (node.key.compareTo(key) < 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;

        return maintainBalance(node);
    }

    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 右旋转
     * y                   x
     * / \                /   \
     * x  T4              z     y
     * / \    --------->  / \   / \
     * z  T3              T1 T2 T3 T4
     * / \
     * T1 T2
     *
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        y.left = T3;
        x.right = y;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return x;
    }

    /**
     * 左旋转
     *
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T1 = x.left;

        y.right = T1;
        x.left = y;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return x;
    }

    /**
     * 判断是否为二分搜索树
     *
     * @return
     */
    public boolean isBst() {
        ArrayList<K> list = new ArrayList();
        inOrder(root, list);

        for (int i = 1; i < list.size(); i++)
            if (list.get(i - 1).compareTo(list.get(i)) > 0)
                return false;
        return true;
    }

    /**
     * 中序遍历
     *
     * @param node
     * @param list
     */
    private void inOrder(Node node, ArrayList list) {
        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);

    }

    /**
     * 判断是否平衡
     *
     * @return
     */
    private boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 查找以node为根中键值为Key的节点
     *
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        else
            return getNode(node.left, key);
    }

    /**
     * 查找最大元素的递归方法
     *
     * @param root
     * @return 节点
     */
    public Node maximum(Node root) {
        if (root.right == null)
            return root;
        return maximum(root.right);
    }

    /**
     * 判断传入节点的右子树是否为空
     * 如果空则此节点为整个树最右的节点也就是最大值节点
     * 此时需要移除该节点，需要将左节点来代替传入的节点
     * 如果传入节点右子树不为空，则删除以右子树为根的树的最大值节点
     * 最后返回传入节点，作为删除了最大值节点的树的根
     *
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
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
    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        Node retNode;
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node nodeRight = node.right;
                node.right = null;
                retNode = nodeRight;
            }else if (node.right == null) {
                Node nodeLeft = node.left;
                node.left = null;
                retNode = nodeLeft;
            } else {
                Node pioneer = maximum(node.left);
                pioneer.left = remove(node.left,pioneer.key);
                pioneer.right = node.right;
                node.left = node.right = null;
                retNode = pioneer;
            }
        }

        if(retNode == null)
            return null;

        return maintainBalance(retNode);
    }

    /**
     * 维护平衡二叉树
     * @param retNode
     * @return
     */
    private Node maintainBalance(Node retNode){
        retNode.height = 1 + Math.max(retNode.left.height, retNode.right.height);

        int balanceFactor = getBalanceFactor(retNode);

        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return null;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
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
