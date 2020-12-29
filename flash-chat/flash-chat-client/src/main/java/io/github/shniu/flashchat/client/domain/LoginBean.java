package io.github.shniu.flashchat.client.domain;

/**
 * @author niushaohan
 * @date 2020/12/25 18
 */
public class LoginBean {
    private String username;
    private String password;

    public LoginBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
