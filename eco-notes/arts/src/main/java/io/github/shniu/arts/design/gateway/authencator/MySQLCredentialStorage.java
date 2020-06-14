package io.github.shniu.arts.design.gateway.authencator;

public class MySQLCredentialStorage implements CredentialStorage {
    @Override
    public String getSecretByAppId(String appId) {
        // Todo 从 MySQL 中查询 appId 对应的 secret
        return null;
    }
}
