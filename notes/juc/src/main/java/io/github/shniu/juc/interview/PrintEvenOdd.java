package io.github.shniu.juc.interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有两个线程分别输出0,2,4,6,8和1,3,5,7,9，编写程序使得输出顺序是0,1,2,3,4,5,6,7,8,9
 */
public class PrintEvenOdd {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(new PrintUtil(lock, 0)).start();
        new Thread(new PrintUtil(lock, 1)).start();
    }
}

class PrintUtil implements Runnable {
    private Lock lock;  // 共享锁
    private int flag;  // 用来标识输出奇数还是偶数

    // 共享资源，受到 lock 的保护
    private static int number = 0;
    private static int MAX = 1000;

    public PrintUtil(Lock lock, int flag) {
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();

            // 超出打印次数的限制，退出
            if (number >= MAX) {
                lock.unlock();
                return;
            }

            // 打印奇数还是偶数的判断
            if (number % 2 == flag) {
                System.out.println(number);
                number++;
            }
            lock.unlock();
        }
    }
}
