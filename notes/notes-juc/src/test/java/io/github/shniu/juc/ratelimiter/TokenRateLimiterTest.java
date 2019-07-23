package io.github.shniu.juc.ratelimiter;

import org.junit.Test;

import static org.junit.Assert.*;

public class TokenRateLimiterTest {

    @Test
    public void givenTokenRateLimiter_shouldCanCreate() {
        TokenRateLimiter limiter = TokenRateLimiter.create(5);
        limiter.acquire();
    }
}