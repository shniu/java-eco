package org.digcredit.beecache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.Caching;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by shniu on 2019/3/25.
 */
public class BeeCachingProviderTest {

    private BeeCachingProvider beeCachingProvider;

    @Before
    public void setUp() throws Exception {
        beeCachingProvider = (BeeCachingProvider) Caching.getCachingProvider("org.digcredit.beecache.BeeCachingProvider");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testURI_whenGetDefault_thenReturnTheDefault() throws Exception {
        URI defaultURI = beeCachingProvider.getDefaultURI();

        Assert.assertNotNull(defaultURI);
        Assert.assertEquals("org.digcredit.beecache.BeeCachingProvider", defaultURI.toString());
    }

    @Test
    public void testGetDefaultManager() {
        CacheManager cacheManager = beeCachingProvider.getCacheManager();

        // not null
        Assert.assertNotNull(cacheManager);
        Assert.assertEquals("org.digcredit.beecache.BeeCacheManager", cacheManager.getClass().getName());
    }

    @Test
    public void testGetManager_givenUriAndClassLoader_thenGetTheManager() {
        URI uri;
        try {
            uri = new URI("org.digcredit.beecache.BeeCacheManager");
        } catch (URISyntaxException e) {
            throw new CacheException("TempCacheManager not found");
        }

        CacheManager cacheManager = beeCachingProvider.getCacheManager(uri, getClass().getClassLoader(), null);
        Assert.assertNotNull(cacheManager);
        Assert.assertEquals("org.digcredit.beecache.BeeCacheManager", cacheManager.getClass().getName());
    }

    @Test
    public void testProviderClose() {
        beeCachingProvider.getCacheManager();
        beeCachingProvider.close();

        WeakHashMap<ClassLoader, HashMap<URI, CacheManager>> cacheManagersByClassLoader = beeCachingProvider.getCacheManagersByClassLoader();
        Assert.assertNotNull(cacheManagersByClassLoader);
        Assert.assertEquals(0, cacheManagersByClassLoader.size());
    }

}