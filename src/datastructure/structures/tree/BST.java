package datastructure.structures.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        root = add(root,e);
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
            node.left = add(node.left,e);
        if(node.e.compareTo(e) < 0)
            node.right = add(node.right,e);

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

    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node curr = queue.remove();
            System.out.println(curr.e);

            if(curr.left != null)
                queue.add(curr.left);
            if(curr.right != null)
                queue.add(curr.right);

        }

    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    public void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.e);

        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 借助栈来实现前序遍历
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node curr = stack.pop();
            System.out.println(curr.e);

            if(curr.right != null)
                stack.push(curr.right);
            if(curr.left != null)
                stack.push(curr.left);

        }

    }

    /**
     * 二分搜索树中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    public void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }


    /**
     * 二分搜索树后续遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    public void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 使用递归的方法实现查找最小元素
     * @return
     */
    public E minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return minimum(root).e;
    }

    /**
     * 查找最小元素的递归方法
     * @param root
     * @return 节点
     */
    public Node minimum(Node root){
        if(root.left == null)
            return root;
        return minimum(root.left);
    }

    /**
     * 使用递归的方法实现查找最大元素
     * @return
     */
    public E maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty!");
        return minimum(root).e;
    }

    /**
     * 查找最大元素的递归方法
     * @param root
     * @return 节点
     */
    public Node maximum(Node root){
        if(root.right == null)
            return root;
        return minimum(root.right);
    }

    /**
     * 通过递归算法删除二分搜索树的最小值
     * @return
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 判断传入节点的左子树是否为空
     * 如果空则此节点为整个树最左的节点也就是最小值节点
     * 此时需要移除该节点，需要将右节点来代替传入的节点
     * 如果传入节点左子树不为空，则删除以左子树为根的树的最小值节点
     * 最后返回传入节点，作为删除了最小值节点的树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 通过递归算法删除二分搜索树的最大值
     * @return
     */
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
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


    public void remove(E e){
        root = remove(root,e);
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
     * @param e
     * @return
     */
    private Node remove(Node node,E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        } else if (e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
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


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    private void generateBSTString(Node node,int depth,StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth)+"null\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);

    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0;i < depth;i++)
            res.append("--");
        return res.toString();
    }
}
