package io.github.shniu.flashchat.common.protocol.command;

/**
 * @author niushaohan
 * @date 2020/12/30 19
 */
public class ChatBean {
    // 文本消息
    private String message;
    // 消息发送的时间戳
    private long timestamp;
    // 消息序列号
    private long sequenceNo;
    // 消息发给谁
    private String to;
    // 消息来自谁
    private String from;

    public ChatBean() {
    }

    public ChatBean(String message, long timestamp, long sequenceNo, String to, String from) {
        this.message = message;
        this.timestamp = timestamp;
        this.sequenceNo = sequenceNo;
        this.to = to;
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(long sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    @Override
    public String toString() {
        return "ChatBean{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", sequenceNo=" + sequenceNo +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
