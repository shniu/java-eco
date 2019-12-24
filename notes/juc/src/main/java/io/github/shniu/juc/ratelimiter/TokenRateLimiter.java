package io.github.shniu.juc.ratelimiter;

// https://github.com/vipshop/vjtools/commit/9eacb861960df0c41b2323ce14da037a9fdc0629
import java.util.concurrent.TimeUnit;

/**
 * 基于令牌桶算法实现的限流器.
 */
public final class TokenRateLimiter {

    // 下一个令牌产生的时间
    private long next = System.nanoTime();

    // 令牌桶中存储的令牌数
    private long storedPermits = 0;

    // 限流器能应对的最大突发流量
    private long maxPermits = 5;

    // 发放令牌的时间间隔
    private long interval = 1000_000_000;

    private TokenRateLimiter() {
    }

    private TokenRateLimiter(long permitsPerSecond) {
        maxPermits = permitsPerSecond;
        interval = (1 / permitsPerSecond) * 1000_000_000;
    }

    /**
     * 创建一个 RateLimiter 的实例.
     *
     * @param permitsPerSecond 每秒允许的最大请求数
     * @return object instance of TokenRateLimiter
     */
    public static TokenRateLimiter create(long permitsPerSecond) {
        return new TokenRateLimiter(permitsPerSecond);
    }

    /**
     * 获取令牌.
     */
    public void acquire() {
        // 获取令牌时的时间
        long now = System.nanoTime();

        // 预占令牌
        long acquiredOn = reserve(now);
        long waitTime = Math.max(acquiredOn - now, 0);

        // 如果等待时间大于0，则阻塞当前线程
        if (waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算能够获取令牌的时间.
     * 令牌是共享资源，存在竞争，需要加锁来控制并发
     *
     * @return 获取到令牌的时间
     */
    private synchronized long reserve(long now) {
        // 同步下次令牌产生时间
        resync(now);

        // 预占下一个产生令牌的时间
        long at = next;

        // 令牌桶中可以提供的令牌
        long canSupplyPermit = Math.min(1, storedPermits);
        // 令牌桶中是否有令牌满足当前的需要
        long needPermits = 1 - canSupplyPermit;
        // 重新计算下一令牌产生的时间
        next = next + interval * needPermits;
        // 计算令牌桶中的令牌数
        storedPermits -= canSupplyPermit;
        return at;
    }

    private void resync(long now) {
        // 请求的时间大于下一次令牌产生的时间，重新计算下一次产生令牌的时间和令牌桶中存储的令牌
        if (now > next) {
            // 新产生的令牌
            long newPermits = (now - next) / interval;
            // 到当前时间，最大能存储的令牌
            storedPermits = Math.max(maxPermits, storedPermits + newPermits);
            // 时间重置
            next = now;
        }
    }
}
