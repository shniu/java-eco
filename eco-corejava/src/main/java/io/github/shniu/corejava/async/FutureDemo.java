package io.github.shniu.corejava.async;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author shniu
 * @date 2019/10/16 15:12:19
 */
public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> future = executorService.submit(task);
        executorService.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>> 主线程执行任务 " + new Date());
        try {
            System.out.println(">>> task 运行结果，等待任务结束 " + future.get() + new Date());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (CancellationException e) {
            System.out.println(">>> 子线程已经取消任务 " + new Date());
        }

        System.out.println(">>> 所有任务执行完毕 " + new Date());
    }
}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("\t 子线程计算开始 " + new Date());
        Thread.sleep(3000);

        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println("\t 子线程计算完成 " + new Date());
        return sum;
    }
}
