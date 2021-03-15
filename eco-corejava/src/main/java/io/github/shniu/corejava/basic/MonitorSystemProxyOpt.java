package io.github.shniu.corejava.basic;

/**
 * 一个监控系统优雅终止的例子的优化
 */
public class MonitorSystemProxyOpt {
    // 自定义的中断标识位, 需要声明为 volatile
    private volatile boolean terminated = false;
    // 启动标识
    private boolean started = false;
    // 采集上报线程
    private Thread rptThread;

    // 开启采集功能
    public synchronized void start() {
        if (started) {
            return;
        }

        started = true;
        terminated = false;
        rptThread = new Thread(() -> {
            while (!terminated) {
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
        // 设置中断
        terminated = true;
        // 中断线程
        rptThread.interrupt();
    }
}
