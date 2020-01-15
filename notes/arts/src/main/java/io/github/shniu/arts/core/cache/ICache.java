package io.github.shniu.arts.core.cache;

public interface ICache {
    void set(String key, Object value);
    Object get(String key);
}
