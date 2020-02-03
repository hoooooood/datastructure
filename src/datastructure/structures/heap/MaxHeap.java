package datastructure.structures.heap;

import datastructure.structures.array.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将数组进行heapify
     * @param arr
     */
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for(int i = parent(data.getSize()-1);i >= 0;i--){
            siftdown(i);
        }
    }

    /**
     * 返回堆的大小
     *
     * @return
     */
    public int getSize() {
        return data.getSize();
    }

    /**
     * 判断堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回父节点的索引值
     *
     * @param index
     * @return
     */
    public int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("there is no parent");
        return (index - 1) / 2;
    }

    /**
     * 返回左孩子节点
     *
     * @param index
     * @return
     */
    public int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回右孩子节点
     *
     * @param index
     * @return
     */
    public int rightCHild(int index) {
        return index * 2 + 2;
    }

    /**
     * 添加元素
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        int index = data.getSize() - 1;
        siftup(index);
    }

    /**
     * 上浮操作
     *
     * @param index
     */
    private void siftup(int index) {

        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(parent(index), index);
            index = parent(index);
        }
    }

    /**
     * 找到堆中元素最大值
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not find max when the heap is empty");
        return data.get(0);
    }

    /**
     * 取出堆中最大值
     * 先找到最大值
     * 然后将数组第一个数与最后一个交换,删除最后一个数
     * 将第一个数进行下沉操作
     *
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftdown(0);
        return ret;
    }

    /**
     * 将最大元素取出，替换成存入的元素
     * @param e
     * @return
     */
    public E repalce(E e){
        E ret = findMax();
        data.set(0,e);
        siftdown(0);
        return ret;
    }

    /**
     * 下沉操作
     * 先判断该元素是否有左子树，没有就不做下沉
     * 有则左子树判断右子树是否存在
     * 接着当前节点与左右子树中元素最大的做交换，
     * 并将新位置进行下一次循环
     *
     * @param index
     */
    private void siftdown(int index) {
        while(leftChild(index) < data.getSize()){
            int j = leftChild(index);
            if(j+1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) >0)
                j = rightCHild(index);
            if(data.get(index).compareTo(data.get(j)) >= 0)
                break;
            data.swap(index,j);
            index = j;
        }

    }

}
