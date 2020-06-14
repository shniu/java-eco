package io.github.shniu.concurrency.singleton;

/**
 * @author niushaohan
 * @date 2020/6/14 19
 */
public class IODHSingleton2 {
    private static final IODHSingleton2 instance = new IODHSingleton2();

    private IODHSingleton2() {
    }

    // 借助于 ClassLoader 的安全加载机制实现的
    public static IODHSingleton2 getInstance() {
        return instance;
    }
}
