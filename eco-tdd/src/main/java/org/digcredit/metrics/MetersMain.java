package org.digcredit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Meter度量一系列事件发生的速率(rate)，例如TPS。Meters会统计每秒，最近1分钟，5分钟，15分钟，还有全部时间的速率。
 *
 * -- Meters ----------------------------------------------------------------------
 * org.digcredit.metrics.MetersMain.request.tps
 *              count = 302
 *          mean rate = 27.45 events/second
 *      1-minute rate = 22.38 events/second
 *      5-minute rate = 21.60 events/second
 *     15-minute rate = 21.47 events/second
 *
 * @author niushaohan
 * @date 2020/9/28 15
 */
public class MetersMain {

    public static Random random = new Random();

    public static void request(Meter meter) {
        System.out.println("request");
        meter.mark();
    }

    public static void request(Meter meter, int n) {
        while (n > 0) {
            request(meter);
            n--;
        }
    }

    public static void main(String[] args) {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Meter meterTps = registry.meter(MetricRegistry.name(MetersMain.class, "request", "tps"));

        while (true) {
            request(meterTps, random.nextInt(50));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
