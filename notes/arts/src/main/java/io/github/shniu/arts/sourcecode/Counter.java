package io.github.shniu.arts.sourcecode;

import sun.misc.Unsafe;

public class Counter {
    private volatile long counter = 0;

    private Unsafe unsafe = ReflectUnsafe.getUnsafe();
    private long offset;

    public Counter() {
        try {
            offset = unsafe.objectFieldOffset(Counter.class.getDeclaredField("counter"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }

    public long getCounter() {
        return counter;
    }
}
