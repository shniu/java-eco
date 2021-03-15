package io.github.shniu.corejava.connpool.custom;

import java.sql.Connection;
import java.util.LinkedList;

public class MyCustomCP {
    private LinkedList<Connection> pool = new LinkedList<>();

    public MyCustomCP(int initialSize) {
        assert initialSize > 0;
        for (int i = 0; i < initialSize; i++) {
            pool.addLast(ConnectionDriver.createConn());
        }
    }

    public void release(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                // 通知所有等待的线程
                pool.notifyAll();
            }
        }
    }

    public Connection borrow(long waitMills) throws InterruptedException {
        synchronized (pool) {
            if (waitMills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + waitMills;
                long remaining = waitMills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }

                Connection conn = null;
                if (!pool.isEmpty()) {
                    conn = pool.removeFirst();
                }

                return conn;
            }
        }
    }
}
