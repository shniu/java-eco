package io.github.shniu.ratelimiter;

import com.google.common.collect.Maps;
import io.github.shniu.ratelimiter.core.RateLimitAlgo;
import io.github.shniu.ratelimiter.rule.ApiLimit;
import io.github.shniu.ratelimiter.rule.RateLimitRule;
import io.github.shniu.ratelimiter.rule.RuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
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
        // 读取配置文件
        InputStream in = null;
        RuleConfig ruleConfig = null;

        try {
            in = this.getClass().getResourceAsStream("/ratelimiter-rule.yml");
            ruleConfig = Optional.ofNullable(in).map(inputStream -> {
                Yaml yaml = new Yaml();
                return yaml.loadAs(inputStream, RuleConfig.class);
            }).orElse(null);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("close file error", e);
                }
            }
        }

        // 转换成限流规则
        this.rule = new RateLimitRule(ruleConfig);
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
            RateLimitAlgo newRateLimitAlgo = new RateLimitAlgo(apiLimit.getLimit());
            rateLimitAlgo = counters.putIfAbsent(counterKey, newRateLimitAlgo);
            if (rateLimitAlgo == null) {
                rateLimitAlgo = newRateLimitAlgo;
            }
        }

        // 返回结果
        return rateLimitAlgo.tryAcquire();
    }
}
