package org.digcredit.project.im.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 自定义通信协议
 *
 * --- 魔数 ---|--- 版本号 ---|--- 序列化算法 ---|--- 指令 ---|--- 数据长度 ---|--- 数据 ---
 *   4 Bytes      1 Bytes         1 Bytes         1 Bytes      4 Bytes       N Bytes
 *
 * Total Bytes: 11 + N
 */
@Data
public abstract class Packet {
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 指令
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
