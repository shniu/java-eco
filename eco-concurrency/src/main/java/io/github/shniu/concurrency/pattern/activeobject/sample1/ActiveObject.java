package io.github.shniu.concurrency.pattern.activeobject.sample1;

import java.util.concurrent.Future;

/**
 * @author niushaohan
 * @date 2020/11/15 23
 */
public interface ActiveObject {
    Future<String> doTask(String name, int score);
    void shutdown();
}
