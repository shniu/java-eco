package io.github.shniu.arts.design.gateway.authencator;

public class RedisCredentialStorage implements CredentialStorage {
    @Override
    public String getSecretByAppId(String appId) {
        // Todo 从 Redis 中查询 appId 对应的 secret
        return null;
    }
}
