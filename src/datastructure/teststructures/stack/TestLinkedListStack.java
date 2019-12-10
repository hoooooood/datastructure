package datastructure.teststructures.stack;

import datastructure.structures.stack.ArrayStack;
import datastructure.structures.stack.LinkedListStack;

public class TestLinkedListStack {
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for(int i = 0; i<10;i++){
            stack.push(i);
            System.out.println(stack);
        }


        stack.pop();
        System.out.println(stack);


    }
}
