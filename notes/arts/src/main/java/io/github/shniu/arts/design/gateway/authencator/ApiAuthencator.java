package io.github.shniu.arts.design.gateway.authencator;

/**
 * 接口鉴权接口
 */
public interface ApiAuthencator {
    boolean auth(String url);
    boolean auth(ApiRequest request);
}
