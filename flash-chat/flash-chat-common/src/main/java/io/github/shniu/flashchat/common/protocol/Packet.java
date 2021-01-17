package io.github.shniu.flashchat.common.protocol;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author niushaohan
 * @date 2021/1/5 18
 */
public abstract class Packet {
    // |--- 魔数 ---|--- 版本号 ---|--- 序列化算法 ---|--- 指令 ---|--- 数据长度 ---|--- 数据 ---|
    //   4 bytes       1 byte           1 byte         1 byte       4 bytes        n bytes
    // Total: 11 bytes + n bytes

    public static final int MAGIC_NUMBER = 0x901014;

    @JsonIgnore
    private byte version = 1;

    @JsonIgnore
    public abstract byte getCommand();

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }
}
