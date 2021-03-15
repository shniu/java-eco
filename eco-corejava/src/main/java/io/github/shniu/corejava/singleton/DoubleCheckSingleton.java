package io.github.shniu.corejava.singleton;

/**
 * @author niushaohan
 * @date 2020/6/14 19
 */
public class DoubleCheckSingleton {
    // 针对双重检查的可能问题，可以考虑将 instance 声明为 volatile 的
    private static volatile DoubleCheckSingleton instance = null;

    // private static DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {
    }

    /**
     * 首先，这种方式创建单例对象是有问题的。
     * <p>
     * 问题是在 instance = new DoubleCheckSingleton(); 这条语句，在编译优化后的执行路径是
     * 1. 分配一块内存M
     * 2. 将内存M的地址赋值给instance变量
     * 3. 在内存M上初始化instance
     * <p>
     * 所以，当有多个线程同时调用 getInstance 时，A 线程获取到锁进入到临界区执行 instance = new DoubleCheckSingleton();
     * 语句，但是在执行完 2 时，发生了上下文切换，B 线程执行，恰好执行到 第一个if，此时 instance已经赋值了，
     * 但是未初始化，这时候访问会报错
     */
    public static DoubleCheckSingleton getInstance() {
        // 第一次检查
        if (instance == null) {
            // 全局锁
            synchronized (DoubleCheckSingleton.class) {
                // 第二次检查
                if (instance == null) {
                    // 这条语句会有问题
                    instance = new DoubleCheckSingleton();
                }
            }
        }

        return instance;
    }
}
