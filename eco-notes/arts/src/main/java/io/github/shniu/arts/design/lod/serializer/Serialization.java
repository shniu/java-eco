package io.github.shniu.arts.design.lod.serializer;

public class Serialization<T> implements Serializable<T>, Deserializable<T> {

    @Override
    public T deserialize(byte[] bytes) {
        // Todo
        return null;
    }

    @Override
    public byte[] serialize(T entry) {
        // Todo
        return new byte[0];
    }
}
