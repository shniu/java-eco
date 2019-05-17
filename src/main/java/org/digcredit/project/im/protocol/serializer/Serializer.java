package org.digcredit.project.im.protocol.serializer;

import org.digcredit.project.im.protocol.Packet;
import org.digcredit.project.im.protocol.serializer.impl.JSONSerializerImpl;

public interface Serializer {
    Serializer DEFAULT = new JSONSerializerImpl();

    byte[] serialize(Object object);
    byte getSerializerAlgorithm();

    Packet deserialize(Class<? extends Packet> requestType, byte[] bytesRead);
}
