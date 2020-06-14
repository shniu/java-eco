package io.github.shniu.ratelimiter.rule.parser;

import io.github.shniu.ratelimiter.rule.RuleConfig;

import java.io.InputStream;

/**
 * @author niushaohan
 * @date 2020/6/14 23
 */
public interface RuleConfigParser {
    RuleConfig parse(InputStream in);
}
