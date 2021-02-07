package org.digcredit.project.async;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author niushaohan
 * @date 2021/2/3 14
 */
public class GuavaAsyncCallback {
    private static int SLEEP_GAP = 500;

    static class MainJob implements Runnable {

        volatile boolean waterDone = false;
        volatile boolean cupDone = false;
        int gap = SLEEP_GAP / 10;

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("开始做事情...");
                    Thread.sleep(gap);
                    System.out.println("做事情结束...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (waterDone || cupDone) {
                    drink(waterDone, cupDone);
                }
            }
        }

        private void drink(boolean waterDone, boolean cupDone) {
            if (waterDone && cupDone) {
                System.out.println("泡茶喝，茶喝完");
                this.waterDone = false;
                this.gap = SLEEP_GAP * 10;
            } else if (!waterDone) {
                System.out.println("烧水失败，没有茶喝了");
            } else if (!cupDone) {
                System.out.println("杯子洗不了，没有茶喝了");
            }
        }
    }

    static class WaterJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            System.out.println("烧水中...");
            Thread.sleep(SLEEP_GAP);
            System.out.println("水烧好...");
            return true;
        }
    }

    static class CupJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            System.out.println("洗杯子中...");
            Thread.sleep(SLEEP_GAP);
            System.out.println("杯子洗好");
            return true;
        }
    }

    public static void main(String[] args) {
        MainJob mainJob = new MainJob();
        new Thread(mainJob).start();

        ExecutorService pool = Executors.newFixedThreadPool(10);
        ListeningExecutorService guavaPool = MoreExecutors.listeningDecorator(pool);

        Callable<Boolean> waterJob = new WaterJob();
        Callable<Boolean> cupJob = new CupJob();

        ListenableFuture<Boolean> waterFuture = guavaPool.submit(waterJob);
        //noinspection UnstableApiUsage
        Futures.addCallback(waterFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean r) {
                if (r != null && r) {
                    mainJob.waterDone = true;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("water failure.");
            }
        }, guavaPool);

        ListenableFuture<Boolean> cupFuture = guavaPool.submit(cupJob);
        //noinspection UnstableApiUsage
        Futures.addCallback(cupFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean result) {
                if (result != null && result) {
                    mainJob.cupDone = true;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("cup failure.");
            }
        }, guavaPool);
    }
}
