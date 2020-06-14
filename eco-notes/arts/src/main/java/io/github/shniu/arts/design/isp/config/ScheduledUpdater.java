package io.github.shniu.arts.design.isp.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledUpdater {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private Updater updater;
    private long initialDelayInSeconds;
    private long periodInSeconds;

    public ScheduledUpdater(Updater updater, long initialDelayInSeconds, long periodInSeconds) {
        this.updater = updater;
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.periodInSeconds = periodInSeconds;
    }

    public void run() {
        executorService.scheduleAtFixedRate(() -> {
            updater.run();
        }, initialDelayInSeconds, periodInSeconds, TimeUnit.SECONDS);
    }
}
