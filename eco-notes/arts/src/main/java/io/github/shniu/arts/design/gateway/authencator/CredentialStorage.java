package io.github.shniu.arts.design.gateway.authencator;

public interface CredentialStorage {
    String getSecretByAppId(String appId);
}
