package io.github.shniu.flashchat.common.protocol.command;

/**
 * @author niushaohan
 * @date 2020/12/30 16
 */
public class LoginResponse {
    private String userId;
    // private String userName;


    public LoginResponse() {
    }

    public LoginResponse(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
