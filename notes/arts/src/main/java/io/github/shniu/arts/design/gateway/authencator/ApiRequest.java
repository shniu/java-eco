package io.github.shniu.arts.design.gateway.authencator;

/**
 * 表示一个请求
 */
public class ApiRequest {
    private String baseUrl;
    private String appId;
    private long ts;
    private String token;

    public ApiRequest(String baseUrl, String appId, long ts, String token) {
        this.baseUrl = baseUrl;
        this.appId = appId;
        this.ts = ts;
        this.token = token;
    }

    // 解析url -> appid, token, ts
    // 构造 ApiRequest
    // 调用 auth(request)
    public static ApiRequest buildFromUrl(String url) {
        // Todo 从url里解析出 token, baseUrl, ts, appId
        String baseUrl = "";
        String appId = "";
        long ts = 0;
        String token = "";
        return new ApiRequest(baseUrl, appId, ts, token);
    }

    public String getOriginalUrl() {
        return baseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public long getTs() {
        return ts;
    }

    public String getToken() {
        return token;
    }
}
