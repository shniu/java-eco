package io.github.shniu.corejava.pattern.activeobject.sample1;

import java.util.concurrent.Callable;

/**
 * @author niushaohan
 * @date 2020/11/16 00
 */
public class Task implements Comparable<Task>, Callable<String> {
    private String name;
    private int score;

    public Task(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.score, o.score);
    }

    @Override
    public String call() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println("Executed by " + name + ", task name: " + this.name + ", score: " + score);
        Thread.sleep(100);
        return "succeed";
    }
}
