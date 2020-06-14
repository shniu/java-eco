package io.github.shniu.arts.design.lod.spider;

import java.nio.charset.StandardCharsets;

public class HTMLDownloader {
    // 依赖注入 IOC
    private NetworkTranspoter networkTranspoter;

    // 使用构造函数注入
    public HTMLDownloader(NetworkTranspoter networkTranspoter) {
        this.networkTranspoter = networkTranspoter;
    }

    public Html downloadHtml(String url) {
        HtmlRequest htmlRequest = new HtmlRequest(url);
        byte[] rawHtml = networkTranspoter.send(htmlRequest.getUrl(),
                htmlRequest.getContent().getBytes(StandardCharsets.UTF_8));
        return new Html(rawHtml);
    }
}

class Html {
    public Html(byte[] rawHtml) {
        // Todo parse bytes to HTML Content
    }
}

class HtmlRequest {
    private String url;

    public HtmlRequest(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getContent() {
        return "";
    }
}
