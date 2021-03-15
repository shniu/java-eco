package io.github.shniu.corejava.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 不使用synchronized和lock，如何实现一个线程安全的单例？
 * https://www.hollischuang.com/archives/1860
 *
 * @author niushaohan
 * @date 2020/6/14 19
 */
public class CASSingleton {
    private static AtomicReference<CASSingleton> reference = new AtomicReference<>();

    private CASSingleton() {
    }

    // 使用 CAS 乐观锁实现的单例模式
    // https://www.hollischuang.com/archives/1866
    public static CASSingleton getInstance() {
        for (; ; ) {
            CASSingleton instance = reference.get();
            if (instance != null) {
                return instance;
            }

            instance = new CASSingleton();
            if (reference.compareAndSet(null, instance)) {
                return instance;
            }
        }

    }
}
