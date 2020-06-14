package io.github.shniu.ratelimiter.core;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author niushaohan
 * @date 2020/6/14 15
 */
public class FixedTimeWindowRateLimitAlgo implements RateLimitAlgo {
    public static final int TRY_LOCK_TIMEOUT = 200;
    private final int limit;
    private Stopwatch stopwatch;
    private AtomicInteger currentCount = new AtomicInteger(0);
    private Lock lock = new ReentrantLock();

    public FixedTimeWindowRateLimitAlgo(int limit) {
        this(limit, Stopwatch.createStarted());
    }

    @VisibleForTesting
    protected FixedTimeWindowRateLimitAlgo(int limit, Stopwatch stopwatch) {
        this.limit = limit;
        this.stopwatch = stopwatch;
    }

    public boolean tryAcquire() {
        int updatedCount = currentCount.incrementAndGet();
        if (updatedCount <= limit) {
            return true;
        }

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                        currentCount.set(0);
                        stopwatch.reset();
                    }
                    updatedCount = currentCount.incrementAndGet();
                    return updatedCount <= limit;
                } finally {
                    lock.unlock();
                }
            } else {
                // throw exception
                throw new RuntimeException("tryAcquire() wait lock too long: " + TRY_LOCK_TIMEOUT + " ms");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("tryAcquire() is interrupted by lock-time-out.", e);
        }
    }
}
