package io.github.shniu.flashchat.common.serialize;

import io.github.shniu.flashchat.common.protocol.Packet;

/**
 * @author niushaohan
 * @date 2021/1/5 16
 */
public interface Serializer {
    Serializer DEFAULT = new Serializer() {
        @Override
        public byte[] serialize(Object object) {
            return new byte[0];
        }

        @Override
        public Packet deserialize(Class<? extends Packet> type, byte[] bytes) {
            return null;
        }
    };

    // 序列化
    byte[] serialize(Object object);

    // 反序列化
    Packet deserialize(Class<? extends Packet> type, byte[] bytes);
}
