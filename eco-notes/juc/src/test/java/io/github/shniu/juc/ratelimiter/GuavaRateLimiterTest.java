package io.github.shniu.juc.ratelimiter;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j(topic = "Test")
public class GuavaRateLimiterTest {

    @Test
    public void givenLimitedResource_whenLargeTrafficAccess_thenShouldBlocking() {
        RateLimiter rateLimiter = RateLimiter.create(20.0);
        ExecutorService es = Executors.newFixedThreadPool(1);

        long prev = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            rateLimiter.acquire();
            es.execute(() -> {
                long cur = System.nanoTime();
                log.info("Time elapsed: {}", (cur - prev) / 1000_000);
                // prev = cur;
            });
        }
    }

    @Test
    public void givenLimitedResource_whenRequestOnce_thenShouldPermitWithoutBlocking()
            throws InterruptedException {
        // given
        RateLimiter rateLimiter = RateLimiter.create(100);

        // when
        int start = ZonedDateTime.now().getSecond();
        rateLimiter.acquire(100);
        Thread.sleep(10);
        int elapsedSeconds = ZonedDateTime.now().getSecond() - start;

        // then
        assertThat(elapsedSeconds <= 1).isTrue();
    }

    @Test
    public void givenLimitedResource_whenUseRateLimiter_thenShouldLimitPermits() {
        // given
        RateLimiter rateLimiter = RateLimiter.create(1);

        // when
        int start = ZonedDateTime.now().getSecond();
        IntStream.range(0, 1000).forEach(i -> {
            log.info("Attempt to acquire permit, i={}", i);
            rateLimiter.acquire();
            log.info("Acquired permit, i={}", i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        int elapsedSeconds = ZonedDateTime.now().getSecond() - start;

        // then
        assertThat(elapsedSeconds >= 10).isTrue();
    }

    @Test
    public void givenLimitedResource_whenTryAcquire_shouldNotBlockIndefinitely() {
        // given
        RateLimiter rateLimiter = RateLimiter.create(1);

        // when
        rateLimiter.acquire();
        boolean result = rateLimiter.tryAcquire(2, 10, TimeUnit.MILLISECONDS);

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void givenLimitedResource_whenMultiThreadAcquire_shouldBlocking() throws ExecutionException, InterruptedException {
        // given
        RateLimiter rateLimiter = RateLimiter.create(5.0);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        // when
        List<Future<?>> futures = submitTask(fixedThreadPool, () -> {
            while (!rateLimiter.tryAcquire()) {
                log.info("Waiting");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            log.info("Acquired");
        });

        for (Future<?> future: futures){
            future.get();
        }
    }

    private List<Future<?>> submitTask(final ExecutorService threadPool, final Runnable command) {
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<?> future = threadPool.submit(command);
            futures.add(future);
        }

        return futures;
    }

    @Test
    public void givenStopWatch_whenUseIt() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(100);
        stopwatch.stop();

        Duration elapsed = stopwatch.elapsed();
        log.info("Elapsed {} nano", elapsed.getNano());
        log.info("Elapsed {} seconds", elapsed.getSeconds());
        log.info("Elapsed {}", stopwatch);
    }
}
