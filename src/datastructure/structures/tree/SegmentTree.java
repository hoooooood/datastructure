package datastructure.structures.tree;

import datastructure.structures.merger.Merger;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    /**
     * 对于一颗满二叉树树来说，所需要的数组空间为约2n
     * 当给定的n形成的树只能形成平衡二叉树时，所需数组空间约为4n
     *
     * @param arr
     */
    public SegmentTree(E[] arr, Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0,0,data.length-1,merger);
        this.merger = merger;
    }

    private void buildSegmentTree(int index,int l,int r,Merger<E> merger){
        if(l==r){
            tree[index] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(index);
        int rightTreeIndex = rightChild(index);

        //防止两个int型数字之和大于int上限
        int mid = l+(r-l)/2;

        buildSegmentTree(leftTreeIndex,l,mid,merger);
        buildSegmentTree(rightTreeIndex,mid+1,r,merger);

        tree[index] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);

    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("index is out of bound");
        return data[index];
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }

    public E query(int queryL,int queryR){
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >=data.length || queryL >= queryR)
            throw new IllegalArgumentException("Index is Illegal");
        return query(0,0,data.length-1,queryL,queryR);
    }

    private E query(int index,int l,int r,int queryL,int queryR){
        if(l == queryL && r == queryR)
            return tree[index];
        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(index);
        int rightTreeIndex = rightChild(index);

        if(queryL >= mid+1)
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        if(queryR <= mid)
            return query(leftTreeIndex,l,mid,queryL,queryR);
        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
        return merger.merge(leftResult,rightResult);

    }

    public void set(int index,E e){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is Illegal");

        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex,int l,int r,int index,E e){
        if(l == r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l+(r-l)/2;
        int leftTreeIndex = leftChild(index);
        int rightTreeIndex = rightChild(index);
        if(index >= mid+1)
            set(rightTreeIndex,mid+1,r,index,e);
        if(index <= mid)
            set(leftTreeIndex,l,mid,index,e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);

    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");
            if(i != tree.length -1)
                res.append(",");
        }
        res.append("]");
        return res.toString();
    }
}
