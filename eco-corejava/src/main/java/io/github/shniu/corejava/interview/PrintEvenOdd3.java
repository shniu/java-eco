package io.github.shniu.corejava.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有两个线程分别输出0,2,4,6,8和1,3,5,7,9，编写程序使得输出顺序是0,1,2,3,4,5,6,7,8,9
 * <p>
 * 思路3 Lock & Condition
 */
public class PrintEvenOdd3 {
    private static volatile int number = 1;
    private static int MAX = 100;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        // 奇数 Condition
        Condition oddCond = lock.newCondition();
        // 偶数 Condition
        Condition evenCond = lock.newCondition();

        Runnable runnable = () -> {
            while (true) {
                lock.lock();

                try {
                    if (number > MAX) {
                        oddCond.signalAll();
                        evenCond.signalAll();
                        return;
                    }

                    // 偶数
                    if (number % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + number);
                        number++;
                        oddCond.signalAll();
                        try {
                            evenCond.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else { // 奇数
                        System.out.println(Thread.currentThread().getName() + number);
                        number++;
                        evenCond.signalAll();
                        try {
                            oddCond.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        };

        // 启动奇数线程
        new Thread(runnable, "奇数").start();

        // 启动偶数线程
        new Thread(runnable, "偶数").start();
    }
}
