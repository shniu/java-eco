package io.github.shniu.flashchat.common.serialize;

/**
 * @author niushaohan
 * @date 2021/1/5 16
 */
public interface Serializer {
    // 序列化
    byte[] serialize(Object object);

    // 反序列化
    Object deserialize(byte[] bytes);
}
