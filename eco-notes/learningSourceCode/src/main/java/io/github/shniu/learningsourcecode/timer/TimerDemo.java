package io.github.shniu.learningsourcecode.timer;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author niushaohan
 * @date 2020/6/4 07
 */
@Slf4j
public class TimerDemo {
    public static void main(String[] args) {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    log.info("task1 finished.");
                } catch (InterruptedException e) {
                    log.error("task error", e);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(task1, 0, 1000);
    }
}
