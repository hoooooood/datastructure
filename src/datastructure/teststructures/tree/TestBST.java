package tree;

import datastructure.structures.tree.BST;

public class TestBST {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,3,6,8,4,2};
        for(int num:nums)
            bst.add(num);


//        bst.levelOrder();

//        bst.preOrder();
//        System.out.println();

//        bst.preOrderNR();
//        System.out.println();

//        bst.inOrder();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();
//
        System.out.println(bst);
        bst.remove(3);
        System.out.println(bst);

    }
}
