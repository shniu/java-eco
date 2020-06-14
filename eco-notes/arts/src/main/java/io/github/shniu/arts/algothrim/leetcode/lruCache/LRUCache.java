package io.github.shniu.arts.algothrim.leetcode.lruCache;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/#/
 * 146. LRU缓存机制
 */
public class LRUCache {

    private int capacity;
    private DoubleList cache;
    private Map<Integer, Node> map;

    public LRUCache() {
    }

    public LRUCache(int capacity) {
        // Collections.synchronizedMap(null);
        // Hashtable
        // HashMap
        // ConcurrentHashMap
        // CopyOnWriteArrayList
        // ArrayBlockingQueue
        // LinkedBlockingQueue
        // ReadWriteLock
        this.capacity = capacity;
        this.cache = new DoubleList();
        this.map = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node curr;
        if ((curr = map.getOrDefault(key, null)) == null) {
            return -1;
        }
        int val = curr.getValue();
        // update to the first
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        Node x = new Node(key, value);

        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            cache.addFirst(x);
            // update map
            map.put(key, x);
        } else {
            if (cache.size() >= capacity) {
                Node last = cache.removeLast();
                map.remove(last.getKey());
            }

            cache.addFirst(x);
            map.put(key, x);
        }
    }
}

class DoubleList {
    private int size;
    private Node head;
    private Node tail;

    public DoubleList() {
        size = 0;
        head = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
        tail = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public void addFirst(Node node) {
        head.getNext().setPrev(node);
        node.setNext(head.getNext());
        head.setNext(node);
        node.setPrev(head);
        size++;
    }

    public void remove(Node node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node.setNext(null);
        node.setPrev(null);
        size--;
    }

    public Node removeLast() {
        Node last = tail.getPrev();
//        tail.getPrev().getNext().setNext(tail);
//        tail.setPrev(tail.getPrev().getPrev());
//        tail.getPrev().setPrev(null);
//        tail.getPrev().setNext(null);
        remove(last);
        return last;
    }

    public int size() {
        return size;
    }
}

class Node {
    private int key, value;
    private Node prev, next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
