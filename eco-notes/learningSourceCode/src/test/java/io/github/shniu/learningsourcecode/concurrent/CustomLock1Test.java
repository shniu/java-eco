package io.github.shniu.learningsourcecode.concurrent;

import org.junit.Assert;
import org.junit.Test;

// import static org.junit.Assert.*;

public class CustomLock1Test {
    private int sum = 0;

    @Test
    public void testCustomLock1_lockAndUnlock() {
        CustomLock1 lock = new CustomLock1();

        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                sum++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sum);
        Assert.assertEquals(20, sum);
    }
}