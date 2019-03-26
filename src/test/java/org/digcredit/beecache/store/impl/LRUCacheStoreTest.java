package org.digcredit.beecache.store.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by shniu on 2019/3/26 0026.
 */
public class LRUCacheStoreTest {

    private LRUCacheStore<String, String> cacheStore;

    @Before
    public void setUp() throws Exception {
        cacheStore = new LRUCacheStore<>();
    }

    @Test
    public void testLRUCacheStore_init() {
        Assert.assertNotNull(cacheStore.getHead());
        Assert.assertNotNull(cacheStore.getTail());
        Assert.assertEquals(cacheStore.getTail(), cacheStore.getHead().getNext());
        Assert.assertEquals(cacheStore.getHead(), cacheStore.getTail().getPrev());
    }

    @Test
    public void testCacheStore_whenPutOneKv_thenSucceed() {
        cacheStore.put("k1", "v1");

        Assert.assertEquals("k1", cacheStore.getHead().getNext().getKey());
        Assert.assertEquals("v1", cacheStore.getHead().getNext().getValue());
    }

    @Test
    public void testCacheStore_whenPutOneKeyTwoTimes_thenGetTheLatestValue() {
        cacheStore.put("name1", "mara");
        cacheStore.put("name1", "lisa");

        Assert.assertEquals("lisa", cacheStore.get("name1"));
    }

    @Test
    public void testCacheStore_whenVisitNotExistsKey_thenReturnNull() {
        cacheStore.put("n1", "100");
        cacheStore.put("n2", "200");
        cacheStore.put("n1", "101");

        Assert.assertNull(cacheStore.get("n3"));
    }

    @Test
    public void testCacheStore_whenPutKVMoreThanCapacity_thenEvictionLRUKey() {
        LRUCacheStore<String, String> cacheStore = new LRUCacheStore<>(5);

        cacheStore.put("k1", "111");
        cacheStore.put("k2", "222");
        cacheStore.put("k3", "333");
        cacheStore.put("k4", "444");
        cacheStore.put("k5", "555");
        cacheStore.put("k6", "666");
        cacheStore.put("k2", "000");

        Assert.assertNull(cacheStore.get("k1"));
        Assert.assertEquals("000", cacheStore.get("k2"));
        Assert.assertEquals("555", cacheStore.get("k5"));
        Assert.assertEquals("666", cacheStore.get("k6"));

        cacheStore.display();
    }

}