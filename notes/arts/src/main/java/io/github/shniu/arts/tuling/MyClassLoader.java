package io.github.shniu.arts.tuling;

public class MyClassLoader extends ClassLoader {
    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassByte(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassByte(String name) {
        return new byte[10];
    }


}
