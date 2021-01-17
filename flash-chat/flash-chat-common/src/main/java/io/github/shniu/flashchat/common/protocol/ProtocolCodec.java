package io.github.shniu.flashchat.common.protocol;

import io.github.shniu.flashchat.common.serialize.Serializer;
import io.github.shniu.flashchat.common.serialize.SerializerAlgorithm;

import java.nio.ByteBuffer;

/**
 * @author niushaohan
 * @date 2021/1/5 18
 */
public class ProtocolCodec {

    public static byte[] encode(Packet packet) {
        byte[] payload = Serializer.DEFAULT.serialize(packet);

        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.putInt(Packet.MAGIC_NUMBER);
        buffer.put(packet.getVersion());
        buffer.put(SerializerAlgorithm.JSON);
        buffer.put(packet.getCommand());
        buffer.putInt(payload.length);
        buffer.put(payload);

        buffer.flip();

        return null;
    }

    public static Packet decode(byte[] bytes) {
        return null;
    }
}
