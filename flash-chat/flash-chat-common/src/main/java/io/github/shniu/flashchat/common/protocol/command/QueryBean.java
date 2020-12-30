package io.github.shniu.flashchat.common.protocol.command;

/**
 * @author niushaohan
 * @date 2020/12/30 19
 */
public class QueryBean {
    private String userId;

    public QueryBean() {
    }

    public QueryBean(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QueryBean{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
