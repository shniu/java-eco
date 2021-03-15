package io.github.shniu.corejava.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://www.baeldung.com/java-synchronous-queue
 * https://blog.csdn.net/yanyan19880509/article/details/52562039
 */
public class SynchronousQueueSample {
    static ExecutorService executorService = Executors.newFixedThreadPool(2);
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static AtomicInteger sharedData = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        // sample1();

        // sample2();

        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        Runnable producer = () -> {
            int producedElem = ThreadLocalRandom.current().nextInt();
            try {
                synchronousQueue.put(producedElem);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                int consumedElem = synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.execute(producer);
        executorService.execute(consumer);

        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
    }

    private static void sample2() throws InterruptedException {
        Runnable producer = () -> {
            int producedElem = ThreadLocalRandom.current().nextInt();
            sharedData.set(producedElem);
            countDownLatch.countDown();
        };

        Runnable consumer = () -> {
            try {
                countDownLatch.await();
                int consumedElem = sharedData.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executorService.execute(producer);
        executorService.execute(consumer);

        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
        assert countDownLatch.getCount() == 0;
    }

    private static void sample1() throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

        Thread put = new Thread(() -> {
            System.out.println("Put thread start");
            try {
                synchronousQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Put thread end");
        });

        Thread take = new Thread(() -> {
            System.out.println("Take thread start");
            try {
                System.out.println("Take " + synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Take thread end");
        });

        put.start();
        Thread.sleep(1000);
        take.start();
    }
}
