package org.digcredit.netty.im.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public abstract class Packet {

    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
