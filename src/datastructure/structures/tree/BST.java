package datastructure.structures.tree;

/**
 * 二分搜索树
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private class Node{
        private E e;
        private Node left,right;

        public Node(E e){
            this.e = e;
        }

    }

    private Node root;
    private int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向二分搜索树添加元素
     * @param e
     */
    public void add(E e){
        add(root,e);
    }

    /**
     * 向以node为根的二分搜索树添加元素，并返回根。
     * 如果传入的根为null则新建以e为值的新节点并作为返回值
     * @param node
     * @param e
     * @return
     */
    public Node add(Node node,E e){
        if(node == null){
            size++;
            return new Node(e);
        }

        if(node.e.compareTo(e) > 0)
            add(node.left,e);
        if(node.e.compareTo(e) < 0)
            add(node.right,e);

        return node;
    }

    /**
     * 在二分搜索树中查询是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        return contains(root,e);
    }

    /**
     * 在以node为根的二分搜索树中查询e
     * @param node
     * @param e
     * @return
     */
    public boolean contains(Node node,E e){
        if(node == null)
            return false;
        if(node.e.compareTo(e) == 0)
            return true;
        if(node.e.compareTo(e) > 0)
            return contains(node.left,e);
        else
            return contains(node.right,e);
    }


}
