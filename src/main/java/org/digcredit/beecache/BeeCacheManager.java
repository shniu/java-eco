package org.digcredit.beecache;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shniu on 2019/3/25.
 */
public class BeeCacheManager implements javax.cache.CacheManager {
    private final BeeCachingProvider cachingProvider;

    private final Map<String, BeeCache<?, ?>> caches = new ConcurrentHashMap<>();
    private final URI uri;
    private final ClassLoader classLoader;

    private volatile boolean closed;

    public BeeCacheManager(BeeCachingProvider cachingProvider, URI uri, ClassLoader classLoader) {
        this.cachingProvider = cachingProvider;

        Objects.requireNonNull(uri);
        Objects.requireNonNull(classLoader);
        this.uri = uri;
        this.classLoader = classLoader;

        closed = false;
    }

    @Override
    public CachingProvider getCachingProvider() {
        return cachingProvider;
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
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
        return caches.keySet();
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

            cachingProvider.releaseCacheManager(uri, classLoader);

            for (BeeCache<?, ?> cache : caches.values()) {
                cache.close();
            }
            caches.clear();
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
