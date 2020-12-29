package io.github.shniu.flashchat.common.protocol;

/**
 * @author niushaohan
 * @date 2020/12/25 18
 */
public class Command {
    private Header header;
    private byte[] payload;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }
}
