package org.digcredit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 最简单的度量指标，只有一个简单的返回值，例如，我们想衡量一个待处理队列中任务的个数.
 *
 * @author niushaohan
 * @date 2020/9/28 15
 */
public class GaugeMain {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        MetricRegistry registry = new MetricRegistry();

        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        registry.register(MetricRegistry.name(GaugeMain.class, "queue", "size"),
                (Gauge<Integer>) queue::size);

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.add("job-110");
        }
    }
}
