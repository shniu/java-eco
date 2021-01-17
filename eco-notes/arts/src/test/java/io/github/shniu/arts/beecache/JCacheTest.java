package io.github.shniu.arts.beecache;

import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * Created by shniu on 2019/3/25.
 */
public class JCacheTest {

    private String cacheName = "sampleCache";

    @Test
    public void testJCache() {
        // CachingProvider cachingProvider = Caching.getCachingProvider();
        CachingProvider cachingProvider = Caching.getCachingProvider("com.github.benmanes.caffeine.jcache.spi.CaffeineCachingProvider");
        // CachingProvider cachingProvider = Caching.getCachingProvider("com.hazelcast.cache.HazelcastCachingProvider");
        CacheManager cacheManager = cachingProvider.getCacheManager();
        MutableConfiguration<String, String> cacheConfiguration = new MutableConfiguration<>();
        Cache<String, String> cache = cacheManager.createCache(cacheName, cacheConfiguration);
        cache.put("k1", "v1");
        cache.put("k2", "v2");

        System.out.println(cache.get("k1"));
        System.out.println(cacheManager.getCache(cacheName).get("k2"));
        cacheManager.close();
    }
}
