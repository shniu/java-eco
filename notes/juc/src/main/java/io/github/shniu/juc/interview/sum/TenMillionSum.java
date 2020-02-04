package io.github.shniu.juc.interview.sum;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.LongAdder;

public class TenMillionSum {
    private static final int TOTAL_LIMIT = 10000000;

    private static final int SIZE_PER_TASK = 2000000;

    private static int availableProcessors = Runtime.getRuntime().availableProcessors();

    // 单线程计算
    public static void singleThread(int[] arr) {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        long end = System.currentTimeMillis();
        System.out.println("Single thread 耗时：" + (end - start) / 1000.0 + ", 计算结果：" + sum);
    }

    // 最基础多线程版本
    @SuppressWarnings("Duplicates")
    public static void concurrentV1(int[] arr) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors + 1);
        LongAdder adder = new LongAdder();
        List<List<int[]>> taskList = Lists.partition(Collections.singletonList(arr), SIZE_PER_TASK);
        int taskSize = taskList.size();
        CountDownLatch latch = new CountDownLatch(taskSize);

        long start = System.currentTimeMillis();
        for (int i = 0; i < taskSize; i++) {
            int[] task = taskList.get(i).get(0);
            executor.submit(() -> {
                try {
                    for (int num : task) {
                        adder.add(num);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        long end = System.currentTimeMillis();
        System.out.println("Concurrent v1 耗时：" + (end - start) / 1000.0 + ", 计算结果：" + adder.intValue());
        executor.shutdown();
    }

    // 改进1
    @SuppressWarnings("Duplicates")
    public static void concurrentV2(int[] arr) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(availableProcessors + 1);

        List<List<int[]>> taskList = Lists.partition(Collections.singletonList(arr), SIZE_PER_TASK);
        int taskSize = taskList.size();
        CountDownLatch latch = new CountDownLatch(taskSize);

        int[] result = new int[taskSize];
        long start = System.currentTimeMillis();
        for (int i = 0; i < taskSize; i++) {
            int[] task = taskList.get(i).get(0);
            final int index = i;
            executor.submit(() -> {
                try {
                    for (int num : task) {
                        result[index] += num;
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        int sum = 0;
        for (int r : result) {
            sum += r;
        }

        long end = System.currentTimeMillis();
        System.out.println("Concurrent v1 耗时：" + (end - start) / 1000.0 + ", 计算结果：" + sum);
        executor.shutdown();
    }

    // 改进2：stream
    public static void concurrentV3(List<Integer> list) {
        long start = System.currentTimeMillis();
        int sum = list.stream().mapToInt(value -> value).sum();
        long end = System.currentTimeMillis();
        System.out.println("Concurrent v1 耗时：" + (end - start) / 1000.0 + ", 计算结果：" + sum);
    }

    // 改进3：parallel stream
    public static void concurrentV4(List<Integer> list) {
        long start = System.currentTimeMillis();
        int sum = list.parallelStream().mapToInt(value -> value).sum();
        long end = System.currentTimeMillis();
        System.out.println("Concurrent v1 耗时：" + (end - start) / 1000.0 + ", 计算结果：" + sum);
    }

    // 改进4：fork/join
    public static void concurrentV5(int[] arr) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        Integer sum = forkJoinPool.invoke(new SicForkJoinTask(arr, 0, TOTAL_LIMIT));
        long end = System.currentTimeMillis();
        System.out.println("Concurrent v1 耗时：" + (end - start) / 1000.0 + ", 计算结果：" + sum);
    }

    public static void main(String[] args) throws InterruptedException {

        // 生成 1 千万个整数
        Random random = new Random();
        int arr[] = new int[TOTAL_LIMIT];
        List<Integer> list = new ArrayList<>(TOTAL_LIMIT);

        int curr;
        for (int i = 0; i < TOTAL_LIMIT; i++) {
            curr = random.nextInt(200);
            arr[i] = curr;
            list.add(curr);
        }

        // 计算累加
        singleThread(arr);
        concurrentV1(arr);
        concurrentV2(arr);
        concurrentV3(list);
        concurrentV4(list);
        concurrentV5(arr);
    }

    static class SicForkJoinTask extends RecursiveTask<Integer> {
        int[] arr;
        Integer left;
        Integer right;

        public SicForkJoinTask(int[] arr, Integer left, Integer right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected Integer compute() {
            if (right - left < SIZE_PER_TASK) {
                // 任务足够小时，直接计算
                int sum = 0;
                for (int i = left; i < right; i++) {
                    sum += arr[i];
                }
                return sum;
            }

            // 继续拆分任务
            int middle = left + (right - left) / 2;
            SicForkJoinTask leftTask = new SicForkJoinTask(arr, left, middle);
            SicForkJoinTask rightTask = new SicForkJoinTask(arr, middle, right);
            invokeAll(leftTask, rightTask);
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            return leftResult + rightResult;
        }
    }
}
