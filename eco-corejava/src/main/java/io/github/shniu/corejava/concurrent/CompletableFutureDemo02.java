package io.github.shniu.corejava.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author niushaohan
 * @date 2021/3/23 16
 */
public class CompletableFutureDemo02 {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = asyncTask();

        new Thread(() -> {
            System.out.println("获取结果的线程在监听");
            try {
                Integer res = future.get();
                System.out.println("获取结果的线程获得结果：" + res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

    }

    static CompletableFuture<Integer> asyncTask() {
        System.out.println("异步执行任务开启");
        CompletableFuture<Integer> future = new CompletableFuture<>();

        // 异步执行
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">> 异步任务执行完成，并设置结果");
            future.complete(100);
        }).start();

        return future;
    }
}
