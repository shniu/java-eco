package io.github.shniu.arts.core.cache.bee.store;

/**
 * Created by shniu on 2019/3/25.
 */
public interface CacheStore<K, V> {

    void put(K key, V value);

    V get(K key);

    boolean remove(K key);
}
