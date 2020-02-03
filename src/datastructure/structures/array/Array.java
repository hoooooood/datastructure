package datastructure.structures.array;

/**
 * 自定义数组结构，借助java的数组类型构建
 */
public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造数组
     *
     * @param capacity 容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * 无参构造数组
     */
    public Array() {
        this(10);
    }

    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for(int i = 0 ; i < arr.length; i++)
            data[i] = arr[i];
        size = arr.length;
    }

    /**
     * 获取数组元素个数
     *
     * @return 元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容积
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 根据索引获取元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get element failed!The index is out of bound");
        return data[index];
    }

    /**
     * 根据索引替换元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get element failed!The index is out of bound");
        data[index] = e;
    }

    /**
     * 查询数组是否包含元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i]))
                return true;
        }
        return false;
    }

    /**
     * 查询数组是否包含元素并返回索引
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i]))
                return i;
        }
        return -1;
    }

    /**
     * 移除元素,返回被删除的元素，当数组元素占数组容量的四分之一时将数组缩容成现容量的一半，为的的是优化均摊复杂度，防止复杂度震荡
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove element failed!The index is out of bound");
        E oldData = data[index];

        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];

        size--;
        data[size] = null;

        if (size == data.length / 4)
            resize(data.length / 2);

        return oldData;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除元素
     *
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    /**
     * 在数组的指定位置添加元素，当到达容量上线时，进行扩容，扩容大小为现在数组容量的两倍
     *
     * @param index 索引
     * @param e     元素
     */
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add element failed.Index is out of bound");

        if (size == data.length)
            resize(2 * data.length);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;
        size++;

    }

    /**
     * 在数组末尾添加元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在数组起始位置添加元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 交换两个索引的元素的值
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        if (i < 0 || j < 0 || i > size || j > size)
            throw new IllegalArgumentException("Index is illegal");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d , capacity = %d \n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(',');
        }
        res.append(']');

        return res.toString();
    }

    /**
     * 重新调整数组容量，做法是新建一个指定容量的数组，将现有数组的元素拷贝到新数组中，替换原数组
     *
     * @param capacity
     */
    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
