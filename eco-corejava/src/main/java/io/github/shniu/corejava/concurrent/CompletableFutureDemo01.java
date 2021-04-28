package io.github.shniu.corejava.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author niushaohan
 * @date 2021/3/23 16
 */
public class CompletableFutureDemo01 {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return 100;
        });

        try {
            // get 抛出的是 ExecutionException
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("============");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return 100;
        });
        // 会触发 CompletionException，但这个异常是由于业务异常引起的

        try {
            future2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
