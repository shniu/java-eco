package org.digcredit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Histogram统计数据的分布情况。比如最小值，最大值，中间值，还有中位数，75百分位, 90百分位,
 *   95百分位, 98百分位, 99百分位, 和 99.9百分位的值(percentiles)
 *
 * @author niushaohan
 * @date 2020/9/28 15
 */
public class HistogramsMain {
    public static Random random = new Random();

    public static void main(String[] args) {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());
        registry.register(MetricRegistry.name(HistogramsMain.class, "request", "histogram"), histogram);

        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            histogram.update(random.nextInt(100000));
        }
    }
}
