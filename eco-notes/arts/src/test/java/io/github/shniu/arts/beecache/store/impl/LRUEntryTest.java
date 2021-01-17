package io.github.shniu.arts.beecache.store.impl;

import io.github.shniu.arts.core.cache.bee.store.impl.LRUEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * LRU Entry test
 * Created by shniu on 2019/3/26 0026.
 */
public class LRUEntryTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetKey() throws Exception {
        LRUEntry<String, String> lruEntry = new LRUEntry<>("k1", "v1");
        String key = lruEntry.getKey();
        String value = lruEntry.getValue();

        Assert.assertEquals("k1", key);
        Assert.assertEquals("v1", value);
    }

    @Test
    public void testLinkedEntry_givenSomeEntry_thenGetLinkedList() {
        LRUEntry<String, String> entry1 = new LRUEntry<>("k1", "v1");
        LRUEntry<String, String> entry2 = new LRUEntry<>("k2", "v2");
        LRUEntry<String, String> entry3 = new LRUEntry<>("k3", "v3");

        entry1.setPrev(null);
        entry1.setNext(entry2);

        entry2.setPrev(entry1);
        entry2.setNext(entry3);

        entry3.setPrev(entry2);
        entry3.setNext(null);

        Assert.assertEquals(entry2, entry1.getNext());
        Assert.assertEquals(entry3, entry1.getNext().getNext());

        Assert.assertEquals(entry1, entry3.getPrev().getPrev());
        Assert.assertEquals(entry2, entry3.getPrev());
    }

}