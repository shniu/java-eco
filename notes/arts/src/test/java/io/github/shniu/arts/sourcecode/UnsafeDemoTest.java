package io.github.shniu.arts.sourcecode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class UnsafeDemoTest {
    Random random = new Random();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void givenUnsafeInstance_whenGetNull_thenReturnMemAddress() {
        Unsafe unsafe = ReflectUnsafe.getUnsafe();
        try {
            Field field = Counter.class.getDeclaredField("counter");
            long offset = unsafe.objectFieldOffset(field);
            log.info("offset: {}", offset);
            int res = unsafe.getInt(new Counter(), offset);
            log.info("res: {}", res);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUnsafeInstance_whenUseCAS_thenOpsSuccess() {
        Counter counter = new Counter();
        counter.increment();
        assert counter.getCounter() == 1;

        counter.increment();
        assert counter.getCounter() == 2;

        // 多线程 test
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
                try {
                    Thread.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert counter.getCounter() == 202;
    }

    @Test
    public void givenCounterInstance_whenUseMultiThreads_thenTheCounterResultIsRight() {
        Counter counter = new Counter();

        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.rangeClosed(0, 9)
                .forEach(i -> service.submit(() -> IntStream
                                .rangeClosed(0, 99)
                                .forEach(j -> counter.increment())
                        )
                );

        assert counter.getCounter() == 1000;

    }
}