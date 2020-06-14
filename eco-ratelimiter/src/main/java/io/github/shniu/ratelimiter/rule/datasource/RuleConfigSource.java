package io.github.shniu.ratelimiter.rule.datasource;

import io.github.shniu.ratelimiter.rule.RuleConfig;

/**
 * @author niushaohan
 * @date 2020/6/14 23
 */
public interface RuleConfigSource {
    RuleConfig load();
}
