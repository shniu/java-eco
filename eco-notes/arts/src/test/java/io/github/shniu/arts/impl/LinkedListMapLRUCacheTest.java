package io.github.shniu.arts.impl;

import io.github.shniu.arts.core.cache.impl.LinkedListMapLRUCache;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by shniu on 2019/3/21.
 */
public class LinkedListMapLRUCacheTest {

    private LinkedListMapLRUCache cache;

    @Before
    public void setUp() {
        cache = new LinkedListMapLRUCache(3);
    }

    @Test
    public void testGivenKVThenSetSucceed() {
        cache.set("lm1", "999");
        // Assert.assertEquals("999", cache..get("lm1").value);
        Assert.assertEquals("999", cache.get("lm1"));
    }

    @Test
    public void testGivenTwoSameKeyThenOnlyHaveOneEntry() {
        cache.set("g1", "000");
        cache.set("g2", "777");
        cache.set("g1", "111");
        Assert.assertEquals("111", cache.get("g1"));
        // Assert.assertEquals("g1", cache.getFirst().next.key);
    }

    @Test
    public void testGivenGtCapacityKeysWhenGetNonExistKeyThenGetNull() {
        cache.set("h1", "aaa");
        cache.set("h2", "bbb");
        cache.set("h3", "jjj");
        cache.set("h4", "vvv");
        cache.set("h5", "www");
        cache.set("h2", "www");
        cache.set("h6", "www");
        cache.print();
        Assert.assertEquals("www", cache.get("h5"));
        Assert.assertNull(cache.get("h1"));
        // Assert.assertEquals("h2", cache.getFirst().next.next.key);
    }

    @Test
    public void testGivenTwoTimesGetKeyThenTheKeyAtFirst() {
        cache.set("h1", "aaa");
        cache.set("h2", "bbb");
        cache.set("h3", "jjj");
        cache.set("h4", "vvv");
        cache.get("h2");
        // Assert.assertEquals("h2", cache.getFirst().next.key);
    }

    @Test
    public void testNothing() {
        int[] arr = new int[10];
        String[] strings = new String[8];
        Float[] floats = new Float[9];
        Integer[] t = new Integer[3];
        t[0] = 127;
        t[1] = 127;
        t[2] = 2;

        assert t[0] == t[1];
        assert t[0].equals(t[1]);

        int[] tt = new int[3];
        tt[0] = 2;
        tt[1] = 2;
        tt[2] = 1000;
        assert tt[0] == tt[1];
    }

    class LMLRUCache {
        private int capacity;
        private LinkedHashMap<String, String> cacheStore;

        public LMLRUCache(int capacity) {
            this.capacity = capacity;
            this.cacheStore = new LinkedHashMap<String, String>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                    return size() > capacity;
                }
            };
        }

        public void set(String key, String value) {
            cacheStore.put(key, value);
        }

        public String get(String key) {
            return cacheStore.get(key);
        }
    }
}
