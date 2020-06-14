package io.github.shniu.ratelimiter.rule;

import java.util.List;

/**
 * @author niushaohan
 * @date 2020/6/14 15
 */
public class RuleConfig {
    private List<AppRuleConfig> configs;

    public RuleConfig() {
    }

    public List<AppRuleConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AppRuleConfig> configs) {
        this.configs = configs;
    }

    public static class AppRuleConfig {
        private String appId;
        private List<ApiLimit> limits;

        public AppRuleConfig() {
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public List<ApiLimit> getLimits() {
            return limits;
        }

        public void setLimits(List<ApiLimit> limits) {
            this.limits = limits;
        }
    }
}
