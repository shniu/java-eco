package io.github.shniu.arts.design.gateway.authencator;

import com.google.common.base.Strings;

public class AuthToken {
    private static final long EXPIRED_TIME_INTERVAL = 60 * 1000; // ms
    private String token;
    private long createTime;
    private long expiredTimeInterval;

    public AuthToken(String token, long ts) {
        this.token = token;
        this.createTime = ts;
        expiredTimeInterval = EXPIRED_TIME_INTERVAL;
    }

    public static AuthToken generate(String originalUrl, String appId, String secret, long clientTs) {
        // Todo 使用 sha 加密算法生成 token
        String token = originalUrl + appId + secret + clientTs;
        return new AuthToken(token, clientTs);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > createTime + expiredTimeInterval;
    }

    public boolean match(AuthToken authToken) {
        if (authToken == null || Strings.isNullOrEmpty(authToken.getToken())) {
            return false;
        }

        return !Strings.isNullOrEmpty(token) && token.equals(authToken.getToken());
    }

    public String getToken() {
        return token;
    }
}
