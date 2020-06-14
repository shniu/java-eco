package io.github.shniu.arts.algothrim.leetcode.queue;

/**
 * https://leetcode-cn.com/problems/design-circular-deque
 * 641. 设计循环双端队列
 * 1. 使用链表实现
 */
public class CircularDeque {
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public CircularDeque(int k) {
        this.capacity = k;
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) return false;

        Node newNode = new Node(value);
//        if (head.next == tail) {
//            newNode.next = tail;
//            tail.prev = newNode;
//            newNode.prev = head;
//            head.next = newNode;
//        } else {
//
//        }
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        newNode.prev = head;
        size++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) return false;

        Node newNode = new Node(value);
//        if (tail.prev == head) {
//            tail.prev = newNode;
//            newNode.next = tail;
//            head.next = newNode;
//            newNode.prev = head;
//        } else {
//
//        }
        tail.prev.next = newNode;
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev = newNode;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) return false;

        Node front = head.next;
        head.next.next.prev = head;
        head.next = head.next.next;
        front = null;
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) return false;

        Node last = tail.prev;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        last = null;
        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) return -1;

        return head.next.val;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) return -1;

        return tail.prev.val;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size >= capacity;
    }
}

class Node {
    int val;
    Node next;
    Node prev;

    public Node(int val) {
        this.val = val;
    }
}
