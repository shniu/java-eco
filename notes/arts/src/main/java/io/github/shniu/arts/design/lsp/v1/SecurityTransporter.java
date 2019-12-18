package io.github.shniu.arts.design.lsp.v1;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;

public class SecurityTransporter extends Transporter {
    private String appId;
    private String appToken;

    public SecurityTransporter(HttpClient httpClient, String appId, String appToken) {
        super(httpClient);

        this.appId = appId;
        this.appToken = appToken;
    }

    @Override
    public Response sendRequest(Request request) {
        if (StringUtils.isNotBlank(appId) && StringUtils.isNotBlank(appToken)) {
            request.addPayload("app-id", appId);
            request.addPayload("app-token", appToken);
        }

        return super.sendRequest(request);
    }
}
