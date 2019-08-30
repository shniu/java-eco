package io.github.shniu.juc.demo.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void concurrentAccount() {
        Account a = new Account();
        a.setBalance(10000);

        Runnable echoBalance = () -> System.out.println(a.getBalance());
        new Thread(echoBalance).start();

        new Thread(() -> a.withdraw(100)).start();
        new Thread(echoBalance).start();
        new Thread(() -> a.withdraw(200)).start();
        new Thread(echoBalance).start();
        new Thread(() -> a.withdraw(300)).start();
        new Thread(echoBalance).start();
        new Thread(() -> a.withdraw(400)).start();


        new Thread(() -> a.updatePassword("123")).start();
        new Thread(() -> a.updatePassword("456")).start();
        new Thread(() -> System.out.println(a.getPassword())).start();
        new Thread(() -> a.updatePassword("786")).start();

        System.out.println(a);

    }

    @Test
    public void should_error_when_multi_account_transfer() throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(2000);
        Account c = new Account(3000);

        // 以下的处理方式，就会有并发性问题，账户B的余额每次执行都不是确定的
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread ta = new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.transfer(b, 5);
            });

            Thread tb = new Thread(() -> {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.transfer(b, 5);
            });

            threads.add(ta);
            threads.add(tb);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }

    @Test
    public void should_ok_when_multi_account_transfer() throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(2000);
        Account c = new Account(3000);

        // 以下的处理方式，就会有并发性问题，账户B的余额每次执行都不是确定的
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread ta = new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a.transfer2(b, 5);
            });

            Thread tb = new Thread(() -> {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.transfer2(b, 5);
            });

            threads.add(ta);
            threads.add(tb);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }
}