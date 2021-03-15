package io.github.shniu.corejava.interview.print;

import java.util.concurrent.locks.LockSupport;

public class Solution1_LockSupport {
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char c = 'a'; c <= 'z'; c++) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}
