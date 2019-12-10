package datastructure.teststructures.linkedlist;

import datastructure.structures.linkedlist.LinkedList;

public class TestLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        for(int i = 0;i < 5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(3,5);
        System.out.println(linkedList);

        linkedList.remove(3);
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

    }
}
