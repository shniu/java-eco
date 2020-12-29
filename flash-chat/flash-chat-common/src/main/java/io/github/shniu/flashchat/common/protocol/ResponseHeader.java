package io.github.shniu.flashchat.common.protocol;

/**
 * @author niushaohan
 * @date 2020/12/25 18
 */
public class ResponseHeader extends Header {
    private char code;
    private String error;

    public ResponseHeader(char version, char type, int contentLength) {
        super(version, type, contentLength);
    }

    public char getCode() {
        return code;
    }

    public void setCode(char code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
