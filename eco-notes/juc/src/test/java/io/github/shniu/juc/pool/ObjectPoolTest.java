package io.github.shniu.juc.pool;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ObjectPoolTest {

    @Test
    public void test_objectPoolExec() {
        ObjectPool<Long, String> objectPool = new ObjectPool<>(10, 20L);
        String r = objectPool.exec(t -> {
            log.info("Get {}", t);
            return String.valueOf(t);
        });

        assertThat(r).isEqualTo("20");
    }

    @Test
    public void givenObjectPool_whenMoreThreads_shouldBlocking() throws InterruptedException {
        ObjectPool<Long, String> objectPool = new ObjectPool<>(3, 200L);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            log.info("exec ...");
            threadPool.execute(() -> {
                log.info("inner thread: {}", Thread.currentThread().getId());
                String r = objectPool.exec(t -> {
                    log.info("Get {}", t);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return String.valueOf(t);
                });
                log.info("result: {}", r);
            });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.MINUTES);
    }

}