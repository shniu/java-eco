package io.github.shniu.flashchat.common.protocol.command;

/**
 * @author niushaohan
 * @date 2020/12/30 19
 */
public class AddFriendBean {
    private String ownId;
    private String quoteUserId;

    public AddFriendBean() {
    }

    public AddFriendBean(String ownId, String quoteUserId) {
        this.ownId = ownId;
        this.quoteUserId = quoteUserId;
    }

    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    public String getQuoteUserId() {
        return quoteUserId;
    }

    public void setQuoteUserId(String quoteUserId) {
        this.quoteUserId = quoteUserId;
    }

    @Override
    public String toString() {
        return "AddFriendBean{" +
                "ownId='" + ownId + '\'' +
                ", quoteUserId='" + quoteUserId + '\'' +
                '}';
    }
}
