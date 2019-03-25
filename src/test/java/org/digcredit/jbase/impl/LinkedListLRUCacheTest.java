package org.digcredit.jbase.impl;

import org.digcredit.jbase.ICache;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shniu on 2019/3/21.
 */
public class LinkedListLRUCacheTest {

    @Test
    public void testGivenKVThenPutCacheSucceed() {
        ICache cache = new LinkedListLRUCache();
        cache.set("abc", "1345");
        String value = cache.get("abc");
        assert "1345".equals(value);
    }

    @Test
    public void testGivenInitCapacityWhenSetMoreKeyThenCanReplacementOld() {
        ICache cache = new LinkedListLRUCache(3);
        cache.set("a1", "1");
        cache.set("a2", "2");
        cache.set("a3", "3");
        cache.set("a4", "4");
        cache.set("a5", "5");
        cache.set("a2", "11");
        cache.set("a2", "33");
        cache.set("a2", "22");
        cache.set("a5", "55");
        cache.print();
        String a1Val = cache.get("a1");
        Assert.assertNull(a1Val);
        Assert.assertEquals("22", cache.get("a2"));
        Assert.assertNull(cache.get("a3"));
        Assert.assertEquals("4", cache.get("a4"));
        Assert.assertEquals("55", cache.get("a5"));
    }
}
