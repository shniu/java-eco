package io.github.shniu.corejava.interview.print;

import java.util.concurrent.CountDownLatch;

public class Solution4_sync {
    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    // latch.countDown();

                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (char c = 'a'; c <= 'z'; c++) {
                    System.out.print(c);
                    latch.countDown();

                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }).start();
    }
}
