package io.github.shniu.arts.design.lod.spider;

/**
 * 使用工厂模式，解耦Document和HTMLDownloader
 */
public class DocumentFactory {
    private HTMLDownloader htmlDownloader;

    public DocumentFactory(HTMLDownloader htmlDownloader) {
        this.htmlDownloader = htmlDownloader;
    }

    public Document createDocument(String url) {
        Html html = htmlDownloader.downloadHtml(url);
        return new Document(url, html);
    }
}
