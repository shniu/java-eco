package org.digcredit.jbase.impl;

import org.digcredit.jbase.ICache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shniu on 2019/3/21.
 */
public class LinkedListMapLRUCache implements ICache {

    Map<String, Node> kvStore;

    private Node first;
    private Node last;

    private int capacity;


    public LinkedListMapLRUCache(int capacity) {
        this.capacity = capacity;
        kvStore = new HashMap<>();

        first = new Node(null, null, null, null);
        last = new Node(null, null, null, null);
        first.next = last;
        last.prev = first;
    }

    @Override
    public void set(String key, String value) {

        Node node = kvStore.get(key);
        if (node != null) {
            node.value = value;

            // remove old place
            node.prev.next = node.next;
            node.next.prev = node.prev;

            // add first
            node.next = first.next;
            node.next.prev = node;
            first.next = node;
            node.prev = first;
            return;
        }

        if (kvStore.size() >= capacity) {
            kvStore.remove(last.prev.key);
            // remove tail
            last.prev.prev.next = last;
            last.prev = last.prev.prev;
        }

        node = new Node(key, value, first, first.next);
        if (last.prev == first) {
            last.prev = node;
        } else {
            first.next.prev = node;
        }
        first.next = node;

        kvStore.put(key, node);
    }

    @Override
    public String get(String key) {
        Node found = kvStore.get(key);
        if (found == null) {
            return null;
        }

        // move to head
        found.prev.next = found.next;
        found.next.prev = found.prev;
        found.prev = first;
        first.next = found;

        return found.value;
    }

    @Override
    public void print() {
        StringBuilder sb = new StringBuilder();
        Node iter = first.next;
        while (iter != last) {
            sb.append(iter.key);
            sb.append(":");
            sb.append(iter.value);
            sb.append("\n");

            iter = iter.next;
        }

        System.out.println(sb.toString());
    }

    /* default */
    Node getFirst() {
        return first;
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
