package io.github.shniu.corejava.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

// 线程安全的 add10K
public class Add10K {
    private AtomicLong count = new AtomicLong(0);

    public void add10K() {
        int i = 0;

        while (i++ < 10000) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.getAndIncrement();
        }
    }

    public long getCount() {
        return count.get();
    }

    public static void main(String[] args) {
        Add10K test = new Add10K();

        CountDownLatch latch = new CountDownLatch(3);
        new Thread(() -> {
            test.add10K();
            latch.countDown();
        }).start();
        new Thread(() -> {
            test.add10K();
            latch.countDown();
        }).start();
        new Thread(() -> {
            test.add10K();
            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.getCount()); // 30000
    }
}
