package io.github.shniu.juc.basic;

/**
 * 一个监控系统优雅终止的例子
 */
public class MonitorSystemProxy {
    private boolean started = false;
    private Thread rptThread;

    // 开启采集功能
    public synchronized void start() {
        if (started) {
            return;
        }

        started = true;
        rptThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // 采集上报的逻辑实现
                // report();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // 重新设置状态位
                    Thread.currentThread().interrupt();
                }
            }

            started = false;
        });
        rptThread.start();
    }

    // 终止采集功能
    public synchronized void stop() {
        rptThread.interrupt();
    }
}
