package io.github.shniu.arts.design.lod.spider;

public class Document {
    private String url;

    // Document 类不应该直接依赖 HTMLDownloader，违反了 LOD
    // Document 应该尽可能保持职责简单，只处理和文档相关的功能，至于
    // 如何获取到，怎么获取到，何时获取到是不用关心的
    // private HTMLDownloader downloader;
    private Html html;  // 反过来直接依赖请求返回的内容，然后可以使用构造函数注入的方式降低耦合

    public Document(String url, Html html) {
        this.url = url;
        this.html = html;
    }

    // ... 其他处理 Document 的方法
}
