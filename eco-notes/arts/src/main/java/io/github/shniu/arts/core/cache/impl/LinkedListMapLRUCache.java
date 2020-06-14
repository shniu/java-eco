package io.github.shniu.arts.core.cache.impl;

import io.github.shniu.arts.core.Printer;
import io.github.shniu.arts.core.cache.ICache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LinkedListMapLRUCache implements ICache, Printer {
    private static Logger log = LoggerFactory.getLogger(LinkedListMapLRUCache.class);

    private Map<String, Node> kv;
    private Node head;
    private Node tail;

    private int capacity;
    private int size;

    public LinkedListMapLRUCache() {
        this(10);
    }

    public LinkedListMapLRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        kv = new HashMap<>(capacity);
        head = new Node(null, null, null, null);
        tail = new Node(null, null, null, null);
        head.next = tail;
        tail.prev = head;
    }

    @SuppressWarnings("Duplicates")
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
        Node curr = kv.getOrDefault(key, null);
        // insert
        if (curr == null) {
            curr = new Node(key, value, null, null);
            // check capacity
            if (isFull()) {
                // remove from tail
                removeFromTail();
                size--;
            }
            // insert
            insertToHead(curr);
            size++;

            kv.put(key, curr);
        } else { // update
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
        Node curr = kv.getOrDefault(key, null);
        if (curr != null) {
            // remove
            removeNode(curr);

            // insert
            insertToHead(curr);

            return curr.value;
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
        private Node next;
        private Node prev;

        public Node(String key, Object value, Node next, Node prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
