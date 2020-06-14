package io.github.shniu.ratelimiter.rule;

/**
 * @author niushaohan
 * @date 2020/6/14 23
 */
public class TrieRateLimitRule implements RateLimitRule {
    private RuleConfig ruleConfig;

    public TrieRateLimitRule(RuleConfig ruleConfig) {
        this.ruleConfig = ruleConfig;
    }

    // Todo 实现 Trie 来管理限流规则
    @Override
    public ApiLimit getLimit(String appId, String url) {
        if (ruleConfig == null || ruleConfig.getConfigs() == null) {
            return null;
        }

        for (RuleConfig.AppRuleConfig config : ruleConfig.getConfigs()) {
            if (config.getAppId().equals(appId)) {
                for (ApiLimit limit : config.getLimits()) {
                    if (url.startsWith(limit.getApi())) {
                        return limit;
                    }
                }
            }
        }

        return null;
    }
}
