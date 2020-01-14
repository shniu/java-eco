package io.github.shniu.juc;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 单例模式实现
 * 参考：
 * 不使用synchronized和lock，如何实现一个线程安全的单例？
 * https://www.hollischuang.com/archives/1860
 */
// 这个实现是有线程安全问题的
public class Singleton {
    private static Singleton instance = null;

    private Singleton() {
    }

    /**
     * 首先，这种方式创建单例对象是有问题的。
     * <p>
     * 问题是在 instance = new Singleton(); 这条语句，在编译优化后的执行路径是
     * 1. 分配一块内存M
     * 2. 将内存M的地址赋值给instance变量
     * 3. 在内存M上初始化instance
     * <p>
     * 所以，当有多个线程同时调用 getInstance 时，A 线程获取到锁进入到临界区执行 instance = new Singleton();
     * 语句，但是在执行完 2 时，发生了上下文切换，B 线程执行，恰好执行到 第一个if，此时 instance已经赋值了，
     * 但是未初始化，这时候访问会报错
     */
    public static Singleton getInstance() {
        if (instance == null) {  // 第一次检查
            synchronized (Singleton.class) {  // 全局锁
                if (instance == null) {  // 第二次检查
                    instance = new Singleton(); // 这条语句会有问题
                }
            }
        }
        return instance;
    }

    //  针对以上的问题，可以考虑将 instance 声明为 volatile 的
    //  volatile 在这里的作用就是禁用编译优化，禁用缓存
}

// 这个是单例模式的懒汉模式实现
class Singleton2 {
    // 使用 volatile 来禁用编译优化，禁用缓存，让其他线程对该变量的修改可见
    private static volatile Singleton2 instance = null;

    // 私有化构造函数
    private Singleton2() {
    }

    // 所谓懒汉应该是使用时才去创建
    public static Singleton2 getInstance() {
        if (instance == null) {  // 第一次检查，降低获取同步块的开销
            synchronized (Singleton.class) {
                if (instance == null) {  // 第二次检查，为了安全考虑
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}

class Singleton2_2 {
    private static Singleton2_2 instance;

    private Singleton2_2() {
    }

    // 使用 synchronized 将方法串行化，达到单例的目的
    // 但是这种性能稍微差点，上面的双重检查方案可以优化
    public synchronized static Singleton2_2 getInstance() {
        if (null == instance) {
            instance = new Singleton2_2();
        }
        return instance;
    }
}

// 多线程单实例,  比较好的做法  IODH
// 饿汉模式
class Singleton3 {
    private Singleton3() {
    }

    private static class LazySingleton3Holder {
        static Singleton3 instance = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return LazySingleton3Holder.instance;
    }
}

class Singleton3_2 {
    private static Singleton3_2 instance = new Singleton3_2();

    private Singleton3_2() {
    }

    public static Singleton3_2 getInstance() {
        return instance;
    }
}

// 枚举
enum Singletor4 {
    INSTANCE;

    public void method() {
    }
}

class Singleton5 {
    private static final Singleton5 instance = new Singleton5();

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        return instance;
    }
}

// Singleton3 Singleton3_2 Singleton4 Singleton5，都是借助于 ClassLoader 的安全加载机制实现的
// ----

// 使用 CAS 乐观锁实现的单例模式
// https://www.hollischuang.com/archives/1866
class CASSingleton {
    private static final AtomicReference<CASSingleton> INSTANCE = new AtomicReference<>();

    // 私有化构造函数
    private CASSingleton() {

    }

    public static CASSingleton getInstance() {

        for (; ; ) {
            CASSingleton instance = INSTANCE.get();
            if (null != instance) {
                return instance;
            }

            instance = new CASSingleton();
            if (INSTANCE.compareAndSet(null, instance)) {
                return instance;
            }
        }
    }
}
