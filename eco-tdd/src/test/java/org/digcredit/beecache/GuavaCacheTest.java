package org.digcredit.beecache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by shniu on 2019/3/25.
 */
public class GuavaCacheTest {

    // -------------- Guava cache --------------
    // https://www.baeldung.com/guava-cache
    @Test
    public void testGuavaCache_whenCacheMiss_thenValueIsComputed() {
        CacheLoader<String, String> cacheLoader = getCacheLoader();

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().build(cacheLoader);
        assertEquals(0, cache.size());
        assertEquals("HELLO", cache.getUnchecked("hello"));
        assertEquals(1, cache.size());
    }

    @Test
    public void testGuavaCache_whenCacheReachMaxSize_thenEviction() {
        CacheLoader<String, String> loader = getCacheLoader();

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).build(loader);
        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("three");
        cache.getUnchecked("forth");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("FORTH", cache.getIfPresent("forth"));
    }

    private CacheLoader<String, String> getCacheLoader() {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(@Nonnull String key) throws Exception {
                return key.toUpperCase();
            }
        };
        // CacheLoader.from((Function<String, String>) String::toUpperCase);
        return loader;
    }

    @Test
    public void testGuavaCache_whenCacheReachMaxWeight_thenEviction() {
        CacheLoader<String, String> cacheLoader = getCacheLoader();

        Weigher<String, String> weightByLength = (key, value) -> value.length();
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumWeight(16)
                .weigher(weightByLength)
                .build(cacheLoader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("three");
        cache.getUnchecked("forth");
        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));

        cache.getUnchecked("abcdefghijklmno");
        cache.getUnchecked("z");
        printCache(cache);
        assertEquals(2, cache.size());
    }

    private void printCache(LoadingCache<String, String> cache) {
        cache.asMap().forEach((k, v) -> {
            System.out.print(k);
            System.out.print(":");
            System.out.print(v);
            System.out.println("\n");
        });
    }

    @Test
    public void testGuavaCache_whenEntryIdle_thenEviction() throws InterruptedException {
        CacheLoader<String, String> cacheLoader = getCacheLoader();
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(2, TimeUnit.MILLISECONDS)
                .build(cacheLoader);
        cache.getUnchecked("hello");
        assertEquals(1, cache.size());

        cache.getUnchecked("hello");
        Thread.sleep(300);

        cache.getUnchecked("test");
        assertEquals(1, cache.size());
        assertNull(cache.getIfPresent("hello"));
    }

    @Test
    public void testGuavaCache_whenWeakKeyHasNoRef_thenRemoveFromCache() {
        CacheLoader<String, String> cacheLoader = getCacheLoader();

        // GC weak reference
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().weakKeys().build(cacheLoader);

        // GC soft reference
        CacheBuilder.newBuilder().softValues().build(cacheLoader);
    }
}
