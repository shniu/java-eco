package io.github.shniu.juc.demo.queuing;

import java.util.concurrent.Semaphore;

/**
 * 在车站、机场等出租车时，当很多空出租车就位时，为防止过度拥挤，调度员指挥排队等待坐车的队伍一次进来 5 个人上车，
 * 等这 5 个人坐车出发，再放进去下一批，模拟实现这个过程
 */
public class QueuingSemaphoreSample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Worker(semaphore));
            t.start();
        }

        System.out.println("Go...");
        semaphore.release(5);
        while (semaphore.availablePermits() != 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Go...");
        semaphore.release(5);
    }

    static class Worker implements Runnable {
        private String name;
        private Semaphore semaphore;

        public Worker(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Executed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
