package io.github.shniu.arts.beecache;

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

    private CachingProvider beeProvider;
    private CacheManager cacheManager;
    private Cache<String, String> cache;
    private MutableConfiguration<String, String> configuration;

    @Before
    public void setUp() {
        beeProvider = Caching.getCachingProvider("org.digcredit.beecache.BeeCachingProvider");
        cacheManager = beeProvider.getCacheManager();
        configuration = new MutableConfiguration<>();
        configuration.setTypes(String.class, String.class);
    }

    @Test
    public void testBee_whenPutKVAndGetKey_thenSucceed() {
        cache = cacheManager.createCache("c1", configuration);
        cache.put("k1", "hello");
        cache.put("k2", "world");
        Assert.assertEquals("hello", cache.get("k1"));
    }

    @Test
    public void testBee_givenAKey_whenRemove_thenCanNotVisit() {
        cache = cacheManager.createCache("c2", configuration);
        cache.put("uid_1", "9000");
        boolean removed = cache.remove("uid_1");
        Assert.assertTrue(removed);
        Assert.assertNull(cache.get("uid_1"));
    }

}
