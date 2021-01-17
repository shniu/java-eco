package org.digcredit.tdd;

import org.junit.Test;

/**
 * @author niushaohan
 * @date 2021/1/13 23
 */
public class YieldTest {

    @Test
    public void yield() {
        Runnable r = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-----" + i + " before");
                if (i % 20 == 0) {
                    Thread.yield();
                    System.out.println("lll");
                }
                System.out.println(Thread.currentThread().getName() + "-----" + i + " after");
            }
        };

        new Thread(r, "A").start();
        new Thread(r, "B").start();
    }
}
