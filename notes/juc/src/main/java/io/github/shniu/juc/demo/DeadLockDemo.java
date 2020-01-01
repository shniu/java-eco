package io.github.shniu.juc.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockDemo {

    public static void main(String[] args) {
        // deadLock1();
        deadLock2();
    }

    private static void deadLock2() {
        Account a = new Account(200);
        Account b = new Account(200);
        List<Thread> threads = new ArrayList<>();

        // Executor executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                a.transfer(b, 1);
            }));
        }
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                b.transfer(a, 1);
            }));
        }

        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(a.getBalance());
        System.out.println(b.getBalance());
    }

    public static void deadLock1() {
        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("t1");
                }
            }
        }, "LockDemo-1").start();

        new Thread(() -> {
            synchronized (lock2) {
                synchronized (lock1) {
                    System.out.println("t2");
                }
            }
        }, "LockDemo-2").start();
    }
}

// 更加具体的一个死锁的例子
// 这个专注操作
class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public void transfer(Account target, int amt) {
        synchronized (this) {
            synchronized (target) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.balance > amt) {
                    setBalance(balance - amt);
                    target.setBalance(target.getBalance() + amt);
                }
            }
        }
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}
