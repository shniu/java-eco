package io.github.shniu.arts.core.cache.bee.store.impl;

import javax.cache.Cache;

/**
 * Created by shniu on 2019/3/26.
 */
public final class LRUEntry<K, V> implements Cache.Entry<K, V> {

    private K key;
    private V value;

    private LRUEntry<K, V> prev;
    private LRUEntry<K, V> next;

    public LRUEntry(K key, V value) {
        this.key = key;
        this.value = value;
        prev = null;
        next = null;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }

    public void setPrev(LRUEntry<K, V> prev) {
        this.prev = prev;
    }

    public void setNext(LRUEntry<K, V> next) {
        this.next = next;
    }

    public LRUEntry<K, V> getPrev() {
        return prev;
    }

    public LRUEntry<K, V> getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
