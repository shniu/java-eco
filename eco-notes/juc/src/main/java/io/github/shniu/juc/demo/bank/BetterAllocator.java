package io.github.shniu.juc.demo.bank;

import java.util.ArrayList;
import java.util.List;

public class BetterAllocator {

    private static final BetterAllocator instance = new BetterAllocator();
    private BetterAllocator() {
    }

    public static BetterAllocator getInstance() {
        return instance;
    }

    private List<Object> als = new ArrayList<>();

    synchronized void apply(Object from, Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        als.add(from);
        als.add(to);
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        // 尽量使用 notifyAll, 保证所有的线程都有可能被通知到
        // notify 只会每次在等待队列里选择一个线程进行通知，notifyAll 会通知所有线程
        notifyAll();
    }
}
