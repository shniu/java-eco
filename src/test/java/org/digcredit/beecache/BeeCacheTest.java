package org.digcredit.beecache;

import org.junit.Assert;
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
public class BeeCacheTest {

    @Test
    public void testBeeCache_whenSucceed() {
        // CachingProvider cachingProvider = new BeeCachingProvider();

        String name = "sample1";
        CachingProvider beeProvider = Caching.getCachingProvider("org.digcredit.beecache.BeeCachingProvider");
        CacheManager cacheManager = beeProvider.getCacheManager();

        MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
        Cache<String, String> cache = cacheManager.createCache(name, configuration);
        cache.put("k1", "hello");
        cache.put("k2", "world");

        Assert.assertEquals("hello", cache.get("k1"));
        cacheManager.close();
    }
}
