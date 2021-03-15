package io.github.shniu.corejava.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 这个 Bug 在 JDK 8 中存在，但是在 JDK 9 中被修复，修复的结果是：
 * 如果 isDone() 返回了 true，在调用 get() 方法时一定会返回结果，不会返回空或者抛出异常.
 *
 * @author niushaohan
 * @date 2021/2/7 16
 */
public class FutureTaskBugTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<FutureTask<Integer>> a = new AtomicReference<>();

        Runnable task = () -> {
            while (true) {
                FutureTask<Integer> f = new FutureTask<>(() -> 1);
                a.set(f);
                f.run();
            }
        };

        Supplier<Runnable> observe = () -> () -> {
            while (a.get() == null) {

            }

            int c = 0;
            int ic = 0;
            while (true) {
                c++;
                FutureTask<Integer> f = a.get();
                while (!f.isDone()) {
                }

                try {
                    Thread.currentThread().interrupt();
                    f.get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    ic++;
                    System.out.println("InterruptedException observed when isDone() == true "
                            + c + " "
                            + ic + " "
                            + Thread.currentThread());
                }
            }
        };

        CompletableFuture.runAsync(task);
        Stream.generate(observe::get)
                .limit(Runtime.getRuntime().availableProcessors() - 1)
                .forEach(CompletableFuture::runAsync);

        Thread.sleep(1000);
        System.exit(0);
    }
}
