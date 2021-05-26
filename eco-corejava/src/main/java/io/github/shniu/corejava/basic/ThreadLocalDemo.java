package io.github.shniu.corejava.basic;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ThreadLocalDemo {
    private static ThreadLocal<Map<String, String>> local = new ThreadLocal<>();
    private static ThreadLocal<Map<String, String>> local2 = ThreadLocal.withInitial(Maps::newHashMap);

    // private static Map<ThreadLocal, Supplier<? extends S> supplier>

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
