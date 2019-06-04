package org.digcredit.jbase;

/**
 * Cache interface
 * Created by shniu on 2019/3/21.
 */
public interface ICache {
    void set(String key, String value);
    String get(String key);
//    boolean remove(String key);
//    void clear();
    void print();
}
