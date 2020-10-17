package org.digcredit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Timer其实是 Histogram 和 Meter 的结合， histogram 某部分代码/调用的耗时， meter统计TPS。
 *
 * @author niushaohan
 * @date 2020/9/28 15
 */
public class TimerMain {
    public static Random random = new Random();

    public static void main(String[] args) {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Timer timer = registry.timer(MetricRegistry.name(TimerMain.class, "get-latency"));

        Timer.Context ctx;
        while (true) {
            ctx = timer.time();//计时开始
            try {
                Thread.sleep(random.nextInt(1000));//模拟操作耗时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //计时结束，meter：统计总数、每秒、分等调用的次数，histogram：记录每次操作耗时的分布情况
            ctx.stop();
        }
    }
}
