package heap;

import datastructure.structures.heap.MaxHeap;

import java.util.Random;

public class TestMaxHeap {
    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n; i++)
            heap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0; i < n ; i++)
            arr[i] = heap.extractMax();

        for(int i = 1 ; i < n ; i++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("error");
        System.out.println("Test success");


    }
}
