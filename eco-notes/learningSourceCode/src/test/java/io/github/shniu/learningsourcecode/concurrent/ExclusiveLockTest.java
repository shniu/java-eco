package io.github.shniu.learningsourcecode.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ExclusiveLockTest {
    private static int count = 0;

    @Test
    public void testExclusiveLock() {
        ExclusiveLock lock = new ExclusiveLock();

        CountDownLatch latch = new CountDownLatch(1000);

        IntStream.range(0, 1000).forEach(i -> new Thread(() -> {
            lock.lock();
            try {
                IntStream.range(0, 10000).forEach(j -> count++);
            } finally {
                lock.unlock();
            }
            latch.countDown();
        }, "thread-" + i).start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
        assertEquals(10000000, count);
    }

}