package io.github.shniu.corejava.io.wechat;

/**
 * @author niushaohan
 * @date 2020/12/18 19
 */
public class User {
    String uid;
    String nickname;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
