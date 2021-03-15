package io.github.shniu.corejava.singleton;

/**
 * @author niushaohan
 * @date 2020/6/14 19
 */
public class IODHSingleton {
    private IODHSingleton() {
    }

    private static class LazyHolder {
        static IODHSingleton instance = new IODHSingleton();
    }

    // 饿汉模式的实现，借助私有的静态内部类的静态变量
    // 通过类加载机制来保证线程安全
    public static IODHSingleton getInstance() {
        return LazyHolder.instance;
    }
}
