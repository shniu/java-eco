package org.digcredit.netty.im.serialize;

import org.digcredit.netty.im.serialize.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    byte getSerializerAlogrithm();

    byte[] serialize(Object object);
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
