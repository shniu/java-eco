package io.github.shniu.arts.core.cache.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListMapLRUCacheTest {
    private LinkedListMapLRUCache linkedListMapLRUCache;

    @Before
    public void setUp() throws Exception {
        linkedListMapLRUCache = new LinkedListMapLRUCache();
    }

    @Test
    public void test() {
        linkedListMapLRUCache.print();

        linkedListMapLRUCache.set("a", "90");
        linkedListMapLRUCache.set("b", "200");
        linkedListMapLRUCache.set("a", "99");
        linkedListMapLRUCache.get("c");
        linkedListMapLRUCache.set("c", "ccc");

        linkedListMapLRUCache.print();
    }
}