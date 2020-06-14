package io.github.shniu.ratelimiter.rule;

/**
 * @author niushaohan
 * @date 2020/6/14 15
 */
public interface RateLimitRule {
    ApiLimit getLimit(String appId, String url);
}
