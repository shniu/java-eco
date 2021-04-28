package io.github.shniu.corejava.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 深入分析 Java 的 Object，如 Object Header 等
 *
 * 对象头布局
 * synchronized
 *
 * -XX:-UseCompressedOops -XX:+UseBiasedLocking -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatistics
 * -XX:-UseCompressedOops -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -XX:+UnlockDiagnosticVMOptions -XX:+PrintBiasedLockingStatistics
 *
 * @author niushaohan
 * @date 2021/3/29 17
 */
public class DeepInObject {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(60000);

        Object o = new Object();

        ClassLayout classLayoutOfObject = ClassLayout.parseInstance(o);
        System.out.println(classLayoutOfObject.toPrintable());

        int[] a = new int[3];
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        Object lock = new Object();
        synchronized (lock) {
            System.out.println("hello");
            // 轻量级锁
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        System.out.println("MyCat=====");
        Cat myCat = new Cat("Chubby Tiger");
        ClassLayout classLayoutOfMyCat = ClassLayout.parseInstance(myCat);
        System.out.println(classLayoutOfMyCat.toPrintable());

        Runnable runnable = () -> {
            synchronized (myCat) {
                // 重量级锁
                System.out.println(ClassLayout.parseInstance(myCat).toPrintable());

                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(runnable).start();
        }

        Thread.sleep(30 * 1000);
    }

    static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }
    }
}
