package org.digcredit.project.im.serializer.impl;

import com.alibaba.fastjson.JSON;
import org.digcredit.project.im.protocol.Packet;
import org.digcredit.project.im.serializer.Serializer;
import org.digcredit.project.im.serializer.SerializerAlgorithm;

public class JSONSerializerImpl implements Serializer {

    @Override
    public byte[] serialize(final Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public Packet deserialize(Class<? extends Packet> requestType, byte[] bytesRead) {
        return JSON.parseObject(bytesRead, requestType);
    }
}
