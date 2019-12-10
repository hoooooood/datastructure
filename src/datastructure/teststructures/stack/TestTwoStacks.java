package datastructure.teststructures.stack;


import datastructure.structures.stack.ArrayStack;
import datastructure.structures.stack.LinkedListStack;
import datastructure.structures.stack.Stack;

import java.util.Random;

public class TestTwoStacks {

    private static double testQueue(Stack<Integer> stack, int optCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i = 0 ; i < optCount;i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0 ; i < optCount;i++)
            stack.pop();

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;

    }

    public static void main(String[] args) {
        int optCount = 1000;
        double time1 = testQueue(new ArrayStack<Integer>(),optCount);
        double time2 = testQueue(new LinkedListStack<Integer>(),optCount);

        System.out.println("ArrayStack:"+time1+"s");
        System.out.println("LinkedListStack:"+time2+"s");
    }
}
