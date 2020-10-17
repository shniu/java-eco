package org.digcredit.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Counter 就是计数器，Counter 只是用 Gauge 封装了 AtomicLong .
 *
 * @author niushaohan
 * @date 2020/9/28 15
 */
public class CounterMain {

    public static Queue<String> q = new LinkedBlockingQueue<String>();

    public static Counter pendingJobs;

    public static Random random = new Random();

    public static void addJob(String job) {
        pendingJobs.inc();
        q.offer(job);
    }

    public static String takeJob() {
        String result = q.poll();
        if (result != null)
            pendingJobs.dec();
        return result;
    }


    public static void main(String[] args) {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        pendingJobs = registry.counter(MetricRegistry.name(CounterMain.class, "pending-jobs", "size"));

        int num = 0;
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (random.nextDouble() > 0.7) {
                String job = takeJob();
                System.out.println("take job : " + job);
            } else {
                num++;
                String job = "Job-" + num;
                addJob(job);
                System.out.println("add job : " + job);
            }
        }

    }
}
