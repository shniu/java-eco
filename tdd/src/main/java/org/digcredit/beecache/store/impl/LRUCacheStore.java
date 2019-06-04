package org.digcredit.beecache.store.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap + Doubly linked list
 * <p>
 * Created by shniu on 2019/3/26.
 */
public class LRUCacheStore<K, V> implements org.digcredit.beecache.store.CacheStore<K, V> {

    private final int DEFAULT_MAX_CAPACITY = 16;

    private int capacity;
    private Map<K, LRUEntry<K, V>> stores = new HashMap<>();

    // head for linked list
    private LRUEntry<K, V> head;
    // tail for linked list
    private LRUEntry<K, V> tail;

    public LRUCacheStore() {
        capacity = DEFAULT_MAX_CAPACITY;

        initLRUDoublyLinkedList();
    }

    public LRUCacheStore(int capacity) {
        this.capacity = capacity;

        initLRUDoublyLinkedList();
    }

    private void initLRUDoublyLinkedList() {
        head = new LRUEntry<>(null, null);
        tail = new LRUEntry<>(null, null);

        head.setNext(tail);
        tail.setPrev(head);
    }

    @Override
    public void put(K key, V value) {
        LRUEntry<K, V> entry = stores.get(key);
        if (entry == null) {
            if (stores.size() >= capacity) {
                removeTailEntry();
            }

            entry = new LRUEntry<>(key, value);
            stores.put(key, entry);
        } else {
            entry.setValue(value);
            deleteOldPlace(entry);
        }

        insertToHead(entry);
    }

    private void removeTailEntry() {
        LRUEntry<K, V> removedEntry = tail.getPrev();
        tail.setPrev(removedEntry.getPrev());
        removedEntry.getPrev().setNext(tail);
        removedEntry.setNext(null);
        removedEntry.setNext(null);

        stores.remove(removedEntry.getKey());
    }

    @Override
    public V get(K key) {
        LRUEntry<K, V> entry = stores.get(key);
        if (entry == null) {
            return null;
        }

        deleteOldPlace(entry);
        insertToHead(entry);
        return entry.getValue();
    }

    @Override
    public boolean remove(K key) {
        LRUEntry<K, V> removedEntry = stores.remove(key);
        removeEntry(removedEntry);
        return removedEntry != null;
    }

    private void removeEntry(LRUEntry<K, V> removedEntry) {
        removedEntry.getPrev().setNext(removedEntry.getNext());
        removedEntry.getNext().setPrev(removedEntry.getPrev());
        removedEntry.setPrev(null);
        removedEntry.setNext(null);
    }

    private void deleteOldPlace(LRUEntry<K, V> entry) {
        entry.getNext().setPrev(entry.getPrev());
        entry.getPrev().setNext(entry.getNext());
        entry.setNext(null);
        entry.setPrev(null);
    }

    private void insertToHead(LRUEntry<K, V> entry) {
        head.getNext().setPrev(entry);
        entry.setNext(head.getNext());
        head.setNext(entry);
        entry.setPrev(head);
    }

    public LRUEntry<K, V> getHead() {
        return head;
    }

    public LRUEntry<K, V> getTail() {
        return tail;
    }

    public void display() {
        LRUEntry<K, V> entry = head.getNext();
        while (entry != null && entry.getKey() != null) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

            entry = entry.getNext();
        }
    }
}
