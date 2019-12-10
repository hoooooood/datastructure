package datastructure.teststructures.array;

import datastructure.structures.array.Array;

public class TestArray {
    public static void main(String[] args) {
        Array<Integer> arr = new Array();
        for (int i = 0; i < 10; i++)
            arr.addLast(i);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.add(5,100);
        System.out.println(arr);

        arr.remove(arr.getSize()-1);
        System.out.println(arr);

        arr.removeElement(100);
        System.out.println(arr);


    }
}
