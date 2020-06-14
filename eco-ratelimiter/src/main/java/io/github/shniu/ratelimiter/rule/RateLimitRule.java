package io.github.shniu.ratelimiter.rule;

/**
 * @author niushaohan
 * @date 2020/6/14 15
 */
public class RateLimitRule {
    private RuleConfig ruleConfig;

    public RateLimitRule(RuleConfig ruleConfig) {
        this.ruleConfig = ruleConfig;
    }

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
