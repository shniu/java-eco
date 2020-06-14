package io.github.shniu.arts.design.lod.serializer;

public interface Deserializable<T> {
    T deserialize(byte[] bytes);
}
