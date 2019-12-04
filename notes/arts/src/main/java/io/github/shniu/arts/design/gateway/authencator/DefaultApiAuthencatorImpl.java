package io.github.shniu.arts.design.gateway.authencator;

public class DefaultApiAuthencatorImpl implements ApiAuthencator {

    private CredentialStorage credentialStorage;

    public DefaultApiAuthencatorImpl() {
        credentialStorage = new MySQLCredentialStorage();
    }

    public DefaultApiAuthencatorImpl(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    @Override
    public boolean auth(String url) {
        ApiRequest request = ApiRequest.buildFromUrl(url);
        return auth(request);
    }

    @Override
    public boolean auth(ApiRequest request) {
        String token = request.getToken();
        String appId = request.getAppId();
        long clientTs = request.getTs();
        String originalUrl = request.getOriginalUrl();

        // 验证token的时间戳
        AuthToken clientAuthToken = new AuthToken(token, clientTs);
        if (clientAuthToken.isExpired()) {
            // or Throw RuntimeException
            return false;
        }

        // 验证token是否合法
        String secret = credentialStorage.getSecretByAppId(appId);
        AuthToken serverAuthToken = AuthToken.generate(originalUrl, appId, secret, clientTs);
        return serverAuthToken.match(clientAuthToken);
    }
}
