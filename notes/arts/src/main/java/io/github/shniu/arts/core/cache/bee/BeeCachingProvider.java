package io.github.shniu.arts.core.cache.bee;

import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.OptionalFeature;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Properties;
import java.util.WeakHashMap;

/**
 * Created by shniu on 2019/3/25.
 */
public class BeeCachingProvider implements CachingProvider {

    private WeakHashMap<ClassLoader, HashMap<URI, CacheManager>> cacheManagersByClassLoader;

    public BeeCachingProvider() {
        cacheManagersByClassLoader = new WeakHashMap<>();
    }

    WeakHashMap<ClassLoader, HashMap<URI, CacheManager>> getCacheManagersByClassLoader() {
        return cacheManagersByClassLoader;
    }

    @Override
    public synchronized CacheManager getCacheManager(URI uri, ClassLoader classLoader, Properties properties) {
        // 弱引用
        // 类加载机制
        // GC

        // check
        URI managerURI = uri == null ? getDefaultURI() : uri;
        ClassLoader managerClassLoader = classLoader == null ? getDefaultClassLoader() : classLoader;
        Properties managerProperties = properties == null ? getDefaultProperties() : properties;

        HashMap<URI, CacheManager> uriCacheManagers = cacheManagersByClassLoader.get(managerClassLoader);
        if (uriCacheManagers == null) {
            uriCacheManagers = new HashMap<>();
        }

        CacheManager cacheManager = uriCacheManagers.computeIfAbsent(managerURI, k ->
                new BeeCacheManager(this, managerURI, managerClassLoader));

        if (!cacheManagersByClassLoader.containsKey(managerClassLoader)) {
            cacheManagersByClassLoader.put(managerClassLoader, uriCacheManagers);
        }

        return cacheManager;
    }

    @Override
    public ClassLoader getDefaultClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public URI getDefaultURI() {
        try {
            return new URI(this.getClass().getName());
        } catch (URISyntaxException e) {
            throw new CacheException("Get default URI error");
        }
    }

    @Override
    public Properties getDefaultProperties() {
        return null;
    }

    @Override
    public CacheManager getCacheManager(URI uri, ClassLoader classLoader) {
        return getCacheManager(uri, classLoader, getDefaultProperties());
    }

    @Override
    public CacheManager getCacheManager() {
        return getCacheManager(getDefaultURI(), getDefaultClassLoader(), null);
    }

    @Override
    public synchronized void close() {
        WeakHashMap<ClassLoader, HashMap<URI, CacheManager>> managersByClassLoader = this.cacheManagersByClassLoader;
        this.cacheManagersByClassLoader = new WeakHashMap<>();

        for (ClassLoader classLoader : managersByClassLoader.keySet()) {
            for (CacheManager cacheManager : managersByClassLoader.get(classLoader).values()) {
                cacheManager.close();
            }
        }
    }

    @Override
    public void close(ClassLoader classLoader) {
        classLoader = classLoader == null ? getDefaultClassLoader() : classLoader;
        HashMap<URI, CacheManager> managerHashMap = cacheManagersByClassLoader.get(classLoader);
        for (CacheManager cacheManager : managerHashMap.values()) {
            cacheManager.close();
        }
        managerHashMap.clear();
        cacheManagersByClassLoader.put(classLoader, new HashMap<>());
    }

    @Override
    public void close(URI uri, ClassLoader classLoader) {

    }

    @Override
    public boolean isSupported(OptionalFeature optionalFeature) {
        return false;
    }

    public synchronized void releaseCacheManager(URI uri, ClassLoader classLoader) {
        HashMap<URI, CacheManager> managerHashMap = cacheManagersByClassLoader.get(classLoader);
        if (managerHashMap != null) {
            managerHashMap.remove(uri);

            if (managerHashMap.size() == 0) {
                cacheManagersByClassLoader.remove(classLoader);
            }
        }
    }
}
