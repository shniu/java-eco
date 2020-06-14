package io.github.shniu.juc.connpool.custom;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCustomCPTest {
    static MyCustomCP cp = new MyCustomCP(10);

    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        long sMills = System.currentTimeMillis();
        // 并发获取连接的线程数
        int threadCount = 100;
        end = new CountDownLatch(threadCount);

        int count = 10;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(new ConnectionRunner(count, got, notGot),
                    "ConnectionRunnerThread");
            t.start();
        }

        start.countDown();
        end.await();

        System.out.println("Total invoke: " + (threadCount * count));
        System.out.println("Got connection: " + got);
        System.out.println("Not got connection: " + notGot);

        long eMills = System.currentTimeMillis();
        System.out.println("Total time: " + (eMills - sMills));
    }

    static class ConnectionRunner implements Runnable {
        private int count;
        private AtomicInteger got;
        private AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                // ...
            }

            while (count > 0) {
                try {
                    Connection connection = cp.borrow(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            // ...
                        } finally {
                            cp.release(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    // ...
                } finally {
                    count--;
                }
            }

            end.countDown();
        }
    }
}
