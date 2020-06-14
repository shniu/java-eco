package io.github.shniu.arts.core.cache.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListLRUCacheTest {

    private LinkedListLRUCache linkedListLRUCache;

    @Before
    public void setUp() throws Exception {
        linkedListLRUCache = new LinkedListLRUCache();
    }

    @Test
    public void testLRUCache() {
        linkedListLRUCache.print();

        linkedListLRUCache.set("a", "100");
        linkedListLRUCache.set("b", "200");
        linkedListLRUCache.set("a", "400");
        linkedListLRUCache.set("c", "600");
        linkedListLRUCache.get("b");

        linkedListLRUCache.print();
    }
}