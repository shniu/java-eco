package io.github.shniu.arts.design.lod.serializer;

public class UserDemo {
    private Serializable<User> serializable;
    private Deserializable<User> deserializable;

    public UserDemo(Serialization<User> serialization) {
        this.deserializable = serialization;
        this.serializable = serialization;
    }

    public void usage1() {
        User user = new User();
        byte[] bytes = serializable.serialize(user);
    }

    public void usage2() {
        User user = deserializable.deserialize(new byte[]{});
    }
}

class User {
    private String id;
    private String name;
    private int age;
}
