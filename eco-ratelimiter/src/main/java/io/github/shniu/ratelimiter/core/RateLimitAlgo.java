package io.github.shniu.ratelimiter.core;

/**
 * @author niushaohan
 * @date 2020/6/14 23
 */
public interface RateLimitAlgo {
    boolean tryAcquire();
}
