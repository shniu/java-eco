package io.github.shniu.juc.ratelimiter;

/**
 * 基于令牌桶算法实现的限流器.
 */
public final class TokenRateLimiter {

    private TokenRateLimiter() {

    }

    /**
     * 创建一个 RateLimiter 的实例.
     *
     * @param permitsPerSecond 每秒允许的最大请求数
     * @return object instance of TokenRateLimiter
     */
    public static TokenRateLimiter create(double permitsPerSecond) {
        return new TokenRateLimiter();
    }

    /**
     * 获取令牌.
     */
    public double acquire() {
        return 0;
    }
}
