package datastructure.teststructures.queue;

import datastructure.structures.queue.LinkedListQueue;

public class TestLInkedListQueue {
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        for(int i = 0;i<10;i++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3==2){
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }
}
