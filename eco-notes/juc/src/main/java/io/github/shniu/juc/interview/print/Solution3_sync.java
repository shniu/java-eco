package io.github.shniu.juc.interview.print;

public class Solution3_sync {

    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
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
