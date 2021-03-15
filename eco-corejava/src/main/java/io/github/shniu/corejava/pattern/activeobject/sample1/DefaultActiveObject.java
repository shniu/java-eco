package io.github.shniu.corejava.pattern.activeobject.sample1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author niushaohan
 * @date 2020/11/16 00
 */
public class DefaultActiveObject implements ActiveObject {
    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public Future<String> doTask(String name, int score) {
        return executorService.submit(new Task(name, score));
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }
}
