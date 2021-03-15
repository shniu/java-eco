package io.github.shniu.corejava.ratelimiter;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;

public class TestGuavaRateLimiter {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        RateLimiter limiter = RateLimiter.create(1);
        limiter.acquire(3);
        System.out.println(stopwatch.elapsed().getSeconds());
        limiter.acquire(20);
        System.out.println(stopwatch.elapsed().getSeconds());
    }
}
