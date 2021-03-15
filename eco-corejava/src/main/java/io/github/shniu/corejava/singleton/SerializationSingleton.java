package io.github.shniu.corejava.singleton;

/**
 * @author niushaohan
 * @date 2020/6/14 19
 */
public class SerializationSingleton {
    private static SerializationSingleton instance;

    private SerializationSingleton() {
    }

    // 使用 synchronized 将方法串行化，达到单例的目的
    // 但是这种性能稍微差点，上面的双重检查方案可以优化
    public synchronized static SerializationSingleton getInstance() {
        if (null == instance) {
            instance = new SerializationSingleton();
        }

        return instance;
    }
}
