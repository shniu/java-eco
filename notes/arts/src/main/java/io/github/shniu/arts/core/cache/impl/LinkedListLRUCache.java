package io.github.shniu.arts.core.cache.impl;

import io.github.shniu.arts.core.Printer;
import io.github.shniu.arts.core.cache.ICache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class LinkedListLRUCache implements ICache, Printer {
    private static Logger log = LoggerFactory.getLogger(LinkedListLRUCache.class);

    private int capacity;
    private int size;

    private Node head;
    private Node tail;

    public LinkedListLRUCache() {
        this(10);
    }

    public LinkedListLRUCache(int capacity) {
        assert capacity > 0;  // for now

        this.capacity = capacity;
        size = 0;
        head = new Node(null, null, null, null);
        tail = new Node(null, null, null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void print() {
        StringBuilder kvBinder = new StringBuilder(20);
        kvBinder.append("\n========== LRU Cache Elements ==========\n");
        Node iter = head.next;
        while (iter != tail) {
            kvBinder.append(iter.key);
            kvBinder.append(": ");
            kvBinder.append(iter.value);
            kvBinder.append("\n");

            iter = iter.next;
        }

        log.info(kvBinder.toString());
    }

    @Override
    public void set(String key, Object value) {
        Node curr = getNodeIfPresent(key);
        if (curr == null) {
            // insert to head
            if (isFull()) {
                removeFromTail();
                size--;
            }
            insertToHead(new Node(key, value, null, null));
            size++;
        } else {
            curr.value = value;

            // 把 curr 挪到 head 的后边
            removeNode(curr);
            insertToHead(curr);
        }
    }

    private void removeFromTail() {
        if (tail.prev == head) return;

        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }

    private boolean isFull() {
        return size >= capacity;
    }

    @Override
    public Object get(String key) {
        Objects.requireNonNull(key);
        Node curr = getNodeIfPresent(key);

        if (curr != null) {
            // 把 curr 挪到 head 的后边
            removeNode(curr);
            insertToHead(curr);
        }

        return curr == null ? null : curr.value;
    }

    private Node getNodeIfPresent(String key) {
        Node curr = head.next;
        while (curr != tail) {
            if (key.equals(curr.key)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    private void removeNode(Node curr) {
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.prev = null;
        curr.next = null;
    }

    private void insertToHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    class Node {
        private String key;
        private Object value;

        private Node prev;
        private Node next;

        public Node(String key, Object value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
