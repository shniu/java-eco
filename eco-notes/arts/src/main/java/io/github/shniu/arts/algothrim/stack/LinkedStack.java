package io.github.shniu.arts.algothrim.stack;

public class LinkedStack implements Stack {
    private static final int DEFAULT_CAPACITY = 16;

    // 使用链表来存储
    private Node top;

    // 栈的元素个数
    private int count;

    // 栈的容量
    private int capacity;

    public LinkedStack() {
        this(DEFAULT_CAPACITY);
    }

    public LinkedStack(int capacity) {
        this.capacity = capacity;
        top = null;
    }

    @Override
    public boolean push(String item) {
        Node newNode = new Node(item, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }

        count++;
        return true;
    }

    @Override
    public String pop() {
        if (top != null) {
            Node tmp = top;
            top = top.getNext();
            count--;
            return tmp.getItem();
        }
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        count = 0;
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String peak() {
        if (top != null) {
            return top.getNext().getItem();
        }
        return null;
    }
}

class Node {
    private String item;
    private Node next;

    public String getItem() {
        return item;
    }

    public Node(String item, Node next) {
        this.item = item;
        this.next = next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }
}
