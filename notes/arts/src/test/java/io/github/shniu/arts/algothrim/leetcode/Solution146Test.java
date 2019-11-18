package io.github.shniu.arts.algothrim.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class Solution146Test {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void givenElementsLteCapacity_shouldOk() {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(10, 10);
        lruCache.put(1, 1);
        int v = lruCache.get(10);
        Assert.assertEquals(10, v);
    }

    @Test
    public void givenElementsGtCapacity_shouldOK() {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(10, 10);
        lruCache.put(1, 1);
        lruCache.put(20, 20);
        lruCache.put(2, 2);

        int v1 = lruCache.get(10);
        int v2 = lruCache.get(1);
        Assert.assertEquals(-1, v1);
        Assert.assertEquals(1, v2);
        Assert.assertEquals("1,2,20", lruCache.toString());

        lruCache.get(20);
        lruCache.put(4, 4);
        Assert.assertEquals("4,20,1", lruCache.toString());
    }
}