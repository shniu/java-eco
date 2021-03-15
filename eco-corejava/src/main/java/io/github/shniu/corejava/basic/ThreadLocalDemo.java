package io.github.shniu.corejava.basic;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalDemo {
    private static ThreadLocal<Map<String, String>> local = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal.withInitial(() -> {
            return 0;
        });

        Thread t1 = new Thread(() -> {
            Map<String, String> config = new HashMap<>();
            config.put("name", "t1");
            local.set(config);
        }, "t1");

        Thread t2 = new Thread(() -> {
            Map<String, String> config = new HashMap<>();
            config.put("name", "t2");
            local.set(config);

            System.out.println(local.get().get("name"));
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
