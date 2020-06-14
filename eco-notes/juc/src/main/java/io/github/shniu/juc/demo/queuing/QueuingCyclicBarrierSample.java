package io.github.shniu.juc.demo.queuing;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

public class QueuingCyclicBarrierSample {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                () -> System.out.println("Go again..."));

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Worker(cyclicBarrier));
            t.start();
        }

    }

    static class Worker implements Runnable {
        private String name;
        private CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 4; i++) {
                    System.out.println("Executed!" + i);
                    barrier.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
