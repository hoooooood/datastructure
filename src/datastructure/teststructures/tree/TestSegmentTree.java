package tree;

import datastructure.structures.merger.Merger;
import datastructure.structures.tree.SegmentTree;

public class TestSegmentTree {
    public static void main(String[] args) {
        Integer[] nums = {-1,0,3,2,4,5,7,8,4};

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a,b) -> a+b);

        System.out.println(segmentTree);

        System.out.println(segmentTree.query(3,5));
        System.out.println(segmentTree.query(0,3));
    }
}
