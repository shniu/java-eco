package io.github.shniu.flashchat.common.protocol;

/**
 * @author niushaohan
 * @date 2020/12/25 18
 */
public class Header {
    private char version;
    private char type;
    private int contentLength;

    public Header(char version, char type, int contentLength) {
        this.version = version;
        this.type = type;
        this.contentLength = contentLength;
    }

    public char getVersion() {
        return version;
    }

    public char getType() {
        return type;
    }

    public int getContentLength() {
        return contentLength;
    }
}
