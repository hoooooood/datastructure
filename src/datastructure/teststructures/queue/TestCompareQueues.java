package datastructure.teststructures.queue;

import datastructure.structures.queue.ArrayQueue;
import datastructure.structures.queue.LinkedListQueue;
import datastructure.structures.queue.LoopQueue;
import datastructure.structures.queue.Queue;

import java.util.Random;

public class TestCompareQueues {

    private static double testQueue(Queue<Integer> queue, int optCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i = 0 ; i < optCount;i++)
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0 ; i < optCount;i++)
            queue.dequeue();

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;

    }

    public static void main(String[] args) {
        int optCount = 1000000;
        double time1 = testQueue(new ArrayQueue<Integer>(),optCount);
        double time2 = testQueue(new LoopQueue<Integer>(),optCount);
        double time3 = testQueue(new LinkedListQueue<Integer>(),optCount);

        System.out.println("ArrayQueue:"+time1+"s");
        System.out.println("LoopQueue:"+time2+"s");
        System.out.println("LinkedListQueue:"+time3+"s");
    }
}
