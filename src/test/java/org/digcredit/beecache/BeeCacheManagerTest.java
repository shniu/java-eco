package org.digcredit.beecache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Created by shniu on 2019/3/25.
 */
public class BeeCacheManagerTest {

    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        CachingProvider cachingProvider = Caching.getCachingProvider("org.digcredit.beecache.BeeCachingProvider");
        cacheManager = cachingProvider.getCacheManager();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCachingProvider() throws Exception {

    }

    @Test
    public void testCreateCache_thenNotNull() {
        MutableConfiguration<String, String> configuration = new MutableConfiguration<>();
        Cache<String, String> cache = cacheManager.createCache("unit-test", configuration);

        Assert.assertNotNull(cache);
        Assert.assertTrue(cache instanceof BeeCache);
        System.out.println(cache);
    }

}