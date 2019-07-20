package io.github.shniu.juc;

public class Singleton {
    private static Singleton instance = null;

    private Singleton() {}

    /**
     * 首先，这种方式创建单例对象是有问题的。
     *
     * 问题是在 instance = new Singleton(); 这条语句，在编译优化后的执行路径是
     *   1. 分配一块内存M
     *   2. 将内存M的地址赋值给instance变量
     *   3. 在内存M上初始化instance
     *
     * 所以，当有多个线程同时调用 getInstance 时，A 线程获取到锁进入到临界区执行 instance = new Singleton();
     * 语句，但是在执行玩 执行路径的2 时，发生了上下文切换，B 线程执行，恰好执行到 第一个if，此时 instance已经赋值了，
     * 但是未初始化，这时候访问会报错
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    //  针对以上的问题，可以考虑将 instance 声明为 volatile 的
    //  volatile 在这里的作用就是禁用编译优化，禁用缓存
}

class Singleton2 {
    private static volatile Singleton2 instance = null;

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}

// 多线程单实例,  比较好的做法  IODH
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
