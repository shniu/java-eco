package org.digcredit.jbase.impl;

import org.digcredit.jbase.ICache;

/**
 * Created by shniu on 2019/3/21.
 */
public class LinkedListLRUCache implements ICache {

    private int capacity = 10;
    private int size = 0;

    private Node first;
    private Node last;

    public LinkedListLRUCache() {
        first = null;
        last = null;
    }

    public LinkedListLRUCache(int capacity) {
        this.capacity = capacity;
        first = null;
        last = null;
    }

    @Override
    public void set(String key, String value) {
        Node node = getNodeIfPresent(key);

        if (node == null) {
            if (size >= capacity) {
                last = last.prev;
                last.next = null;
                size--;
            }
            Node newNode = new Node(key, value, null, first);
            if (first == null && last == null) {
                first = newNode;
                last = newNode;
                size++;
                return;
            }

            first.prev = newNode;
            first = newNode;
            size++;
        } else {
            node.value = value;
            if (last == node) {
                last = last.prev;
                node.next = first;
                node.prev = null;
                first = node;
            }

            if (first != node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.next = first;
                node.prev = null;
                first = node;
            }
        }
    }

    @Override
    public String get(String key) {
        Node node = getNodeIfPresent(key);
        return node == null ? null : node.value;
    }

    private Node getNodeIfPresent(String key) {
        Node iter = first;
        while (iter != null) {
            if (iter.key.equals(key)) {
                break;
            }
            iter = iter.next;
        }
        return iter;
    }

    @Override
    public void print() {
        StringBuilder kvBinder = new StringBuilder(20);

        Node iter = first;
        while (iter != null) {
            kvBinder.append(iter.key);
            kvBinder.append(": ");
            kvBinder.append(iter.value);
            kvBinder.append("\n");

            iter = iter.next;
        }

        System.out.println(kvBinder.toString());
    }

    class Node {
        String key;
        String value;
        Node prev;
        Node next;

        Node(String key, String value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
