package io.github.shniu.arts.design.lod.serializer;

public interface Serializable<T> {
    byte[] serialize(T entry);
}
