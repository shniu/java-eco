package io.github.shniu.juc.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-concurrency-advanced
 */
public class PhaserSample {

    public static void main(String[] args) {
        // given
        ExecutorService executorService = Executors.newCachedThreadPool();
        Phaser phaser = new Phaser(1);
        assert phaser.getPhase() == 0;

        // when
        executorService.submit(new LongRunningAction("thread-1", phaser));
        executorService.submit(new LongRunningAction("thread-2", phaser));
        executorService.submit(new LongRunningAction("thread-3", phaser));

        // then
        phaser.arriveAndAwaitAdvance();
        assert phaser.getPhase() == 1;

        // and
        executorService.submit(new LongRunningAction("thread-4", phaser));
        executorService.submit(new LongRunningAction("thread-5", phaser));
        phaser.arriveAndAwaitAdvance();
        assert phaser.getPhase() == 2;

        phaser.arriveAndDeregister();
    }
}

class LongRunningAction implements Runnable {

    private String threadName;
    private Phaser phaser;

    public LongRunningAction(String threadName, Phaser phaser) {
        this.threadName = threadName;
        this.phaser = phaser;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println("This is phase " + phaser.getPhase());
        System.out.println("Thread " + threadName + " before long running action");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(200);
            System.out.println("Execute " + threadName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arriveAndDeregister();
    }
}
