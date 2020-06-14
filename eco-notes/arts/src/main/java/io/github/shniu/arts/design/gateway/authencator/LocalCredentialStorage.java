package io.github.shniu.arts.design.gateway.authencator;

public class LocalCredentialStorage implements CredentialStorage {
    @Override
    public String getSecretByAppId(String appId) {
        // Todo 从 本地文件 中查询 appId 对应的 secret
        return null;
    }
}
