package io.github.shniu.juc.interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有A，B，C三个线程，A线程输出A，B线程输出B，C线程输出C，要求，同时启动三个线程，按顺序输出ABC，循环10次。
 */
public class PrintABC2 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread a = new Thread(new PrintThread("A", lock, 0));
        Thread b = new Thread(new PrintThread("B", lock, 1));
        Thread c = new Thread(new PrintThread("C", lock, 2));

        a.start();
        b.start();
        c.start();
    }
}

class PrintThread implements Runnable {
    private String name;
    private Lock lock;
    private Integer flag;

    public static int count = 0;
    public static final int MAX = 30;

    public PrintThread(String name, Lock lock, Integer flag) {
        this.name = name;
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();

            if (count >= MAX) {
                lock.unlock();
                return;
            }

            if (count % 3 == flag) {
                System.out.println(name);
                count++;
            }
            lock.unlock();
        }
    }
}
