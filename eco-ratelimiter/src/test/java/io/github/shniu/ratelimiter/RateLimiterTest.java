package io.github.shniu.ratelimiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/6/14 14
 */
@Slf4j
public class RateLimiterTest {

    @Test
    public void testRateLimiter() {
        // given
        String appId = "app1";
        String url = "https://github.com/v1/topic/java";

        RateLimiter rateLimiter = new RateLimiter();

        // when
        boolean passed = rateLimiter.limit(appId, url);

        // then
        if (passed) {
            log.info("do business logic");
        } else {
            log.info("blocked");
        }
        assertTrue(passed);

    }

}