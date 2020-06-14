package io.github.shniu.juc.interview;

/**
 * 有两个线程分别输出0,2,4,6,8和1,3,5,7,9，编写程序使得输出顺序是0,1,2,3,4,5,6,7,8,9
 */
public class PrintEvenOdd2 {
    // 共享资源，多个线程之间争抢访问
    private static volatile int number = 1;

    public static void main(String[] args) {
        // 互斥锁，用lock来保护共享资源
        final Object lock = new Object();

        Runnable runnable = () -> {
            // 只有获取到锁才能进入临界区执行代码
            synchronized (lock) {
                while (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + number++);
                    lock.notifyAll();
                    /*try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    try {
                        lock.wait();
                        System.out.println("收到 notify，继续执行" + number);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 退出，让处于 wait 的所有线程进入就绪队列，这样就能保证线程正常退出
                // 否则会一直处于等待状态
                lock.notifyAll();
            }
        };

        // 启动打印奇数的线程
        new Thread(runnable, "奇数线程").start();
        // 启动打印偶数的线程
        new Thread(runnable, "偶数线程").start();
    }
}
