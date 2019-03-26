package org.digcredit.beecache.store.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shniu on 2019/3/26.
 */
public class LRUCacheStore<K, V> implements org.digcredit.beecache.store.CacheStore<K,V> {

    private int capacity;
    private Map<K, V> stores = new HashMap<>();

    // head for linked list
    private LRUNode head;
    // tail for linked list
    private LRUNode tail;

    @Override
    public void put(K key, V value) {

    }
}
