package io.github.shniu.dubbo.rpc.sample.api;

import java.io.Serializable;

/**
 * @author niushaohan
 * @date 2021/5/13 08
 */
public class User implements Serializable {
    private String uid;
    private String name;
    private long registerTs;

    public User() {
    }

    public User(String uid, String name, long registerTs) {
        this.uid = uid;
        this.name = name;
        this.registerTs = registerTs;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRegisterTs() {
        return registerTs;
    }

    public void setRegisterTs(long registerTs) {
        this.registerTs = registerTs;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", registerTs=" + registerTs +
                '}';
    }
}
