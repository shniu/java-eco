package org.digcredit.beecache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Bee cache
 * Created by shniu on 2019/3/25.
 */
public class BeeTest {

    private CacheManager cacheManager;
    private Cache<String, String> cache;
    private String defaultCacheName = "sample1";

    @Before
    public void setUp() {
        // CachingProvider cachingProvider = new BeeCachingProvider();

        CachingProvider beeProvider = Caching.getCachingProvider("org.digcredit.beecache.BeeCachingProvider");
        cacheManager = beeProvider.getCacheManager();

        MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
        configuration.setTypes(String.class, String.class);
        cache = cacheManager.createCache(defaultCacheName, configuration);
    }

    @Test
    public void testBee_whenPutKVAndGetKey_thenSucceed() {
        cache.put("k1", "hello");
        cache.put("k2", "world");
        Assert.assertEquals("hello", cache.get("k1"));
        cacheManager.close();
    }

    @Test
    public void testBee_givenAKey_whenRemove_thenCanNotVisit() {
        cache.put("uid_1", "9000");
        boolean removed = cache.remove("uid_1");
        Assert.assertTrue(removed);
        Assert.assertNull(cache.get("uid_1"));
        cacheManager.close();
    }

}
