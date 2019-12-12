package io.github.shniu.juc.basic;

import java.util.concurrent.TimeUnit;

/**
 * 测试中断
 */
public class Interrupted {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        SleepUtils.second(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        SleepUtils.second(2);
    }
}

class SleepRunner implements Runnable {

    @Override
    public void run() {
        while (true) {
            // 保证可以退出循环，否则会死循环
            if (Thread.interrupted()) break;

            // ... 其他业务代码

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                // 重置中断状态位
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}

class BusyRunner implements Runnable {

    @Override
    public void run() {
        while (true) {
            // 耗时的业务代码
            if (Thread.interrupted()) {
                System.out.println("Busy Thread 接收到 interrupt");
                Thread.currentThread().interrupt();
                break;
            }
        }

        while (true) {

        }
    }
}
