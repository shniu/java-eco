package io.github.shniu.ratelimiter;

import com.google.common.collect.Maps;
import io.github.shniu.ratelimiter.core.FixedTimeWindowRateLimitAlgo;
import io.github.shniu.ratelimiter.core.RateLimitAlgo;
import io.github.shniu.ratelimiter.rule.ApiLimit;
import io.github.shniu.ratelimiter.rule.RateLimitRule;
import io.github.shniu.ratelimiter.rule.RuleConfig;
import io.github.shniu.ratelimiter.rule.TrieRateLimitRule;
import io.github.shniu.ratelimiter.rule.datasource.FileRuleConfigSource;
import io.github.shniu.ratelimiter.rule.datasource.RuleConfigSource;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentMap;

/**
 * @author niushaohan
 * @date 2020/6/14 11
 */
@Slf4j
public class RateLimiter {
    private RateLimitRule rule;
    private ConcurrentMap<String, RateLimitAlgo> counters = Maps.newConcurrentMap();

    public RateLimiter() {
        RuleConfigSource configSource = new FileRuleConfigSource();
        RuleConfig ruleConfig = configSource.load();
        rule = new TrieRateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) {
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if (null == apiLimit) {
            return true;
        }

        // 根据限流规则和历史流量判断是否允许通过
        String counterKey = appId + ":" + apiLimit.getApi();
        RateLimitAlgo rateLimitAlgo = counters.get(counterKey);
        if (rateLimitAlgo == null) {
            RateLimitAlgo newAlgo = new FixedTimeWindowRateLimitAlgo(apiLimit.getLimit());

            rateLimitAlgo = counters.putIfAbsent(counterKey, newAlgo);
            if (rateLimitAlgo == null) {
                rateLimitAlgo = newAlgo;
            }
        }

        // 返回结果
        return rateLimitAlgo.tryAcquire();
    }
}
