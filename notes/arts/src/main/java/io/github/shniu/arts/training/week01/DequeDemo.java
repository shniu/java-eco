package io.github.shniu.arts.training.week01;

import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();

        // add element to stack
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        // check the first element
        String peekFirst = deque.peekFirst();
        System.out.println(peekFirst);
        assert peekFirst.equals("c");
        System.out.println(deque);

        // pop
        while (deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);
    }
}
