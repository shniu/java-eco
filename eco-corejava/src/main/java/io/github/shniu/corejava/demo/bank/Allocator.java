package io.github.shniu.corejava.demo.bank;

import java.util.ArrayList;
import java.util.List;

// 这个类的实例应该是单例的
public class Allocator {

    private static final Allocator allocator = new Allocator();

    public static Allocator getInstance() {
        return allocator;
    }

    private Allocator() {
    }

    private List<Object> als = new ArrayList<>();

    // 申请资源
    synchronized boolean apply(Object from, Object to) {
        if (als.contains(from) || als.contains(to)) {
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }

    // 释放资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
    }
}
