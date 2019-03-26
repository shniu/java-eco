package org.digcredit.beecache;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shniu on 2019/3/25.
 */
public class BeeCacheManager implements javax.cache.CacheManager {
    private CachingProvider cachingProvider;

    private Map<String, BeeCache<?, ?>> caches = new ConcurrentHashMap<>();

    private boolean closed = false;

    public BeeCacheManager(CachingProvider cachingProvider) {
        this.cachingProvider = cachingProvider;
    }

    @Override
    public CachingProvider getCachingProvider() {
        return cachingProvider;
    }

    @Override
    public URI getURI() {
        try {
            return new URI(this.getClass().getName());
        } catch (URISyntaxException e) {
            throw new CacheException("URI?");
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName, C configuration) throws IllegalArgumentException {

        if (isClosed()) {
            throw new CacheException("Cache manager closed.");
        }

        Objects.requireNonNull(cacheName);

        synchronized (caches) {
            BeeCache<?, ?> beeCache = caches.get(cacheName);
            if (beeCache == null) {
                beeCache = new BeeCache<>();
                caches.put(cacheName, beeCache);
                //noinspection unchecked
                return (Cache<K, V>) beeCache;
            } else {
                throw new CacheException("A cache named " + cacheName + " already exists.");
            }
        }
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType, Class<V> valueType) {
        return null;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        if (isClosed()) {
            throw new CacheException("Cache manager closed.");
        }
        //noinspection unchecked
        return (Cache<K, V>) caches.get(cacheName);
    }

    @Override
    public Iterable<String> getCacheNames() {
        return null;
    }

    @Override
    public void destroyCache(String cacheName) {

    }

    @Override
    public void enableManagement(String cacheName, boolean enabled) {

    }

    @Override
    public void enableStatistics(String cacheName, boolean enabled) {

    }

    @Override
    public void close() {
        synchronized (this) {
            if (isClosed()) {
                return;
            }

            for (BeeCache<?, ?> entries : caches.values()) {
                entries.close();
            }
            caches.clear();
            caches = new HashMap<>();
            closed = true;
        }
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        return null;
    }
}
