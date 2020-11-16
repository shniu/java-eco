package io.github.shniu.concurrency.pattern.sample1;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author niushaohan
 * @date 2020/11/15 23
 */
public class ActiveObject {
    class Task implements Comparable<Task> {
        private String name;
        private int score;

        public Task(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Task o) {
            return Integer.compare(this.score, o.score);
        }
    }

    // Dispatch Queue, also called Activation Queue
    PriorityBlockingQueue<Task> dispatchQueue = new PriorityBlockingQueue<>();

    public ActiveObject() {
        // Scheduler
        new Thread(() -> {
            while (true) {
                try {
                    Task task = dispatchQueue.take();
                    System.out.println("Task name is " + task.getName());
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    public void doTask(String name, int score) {
        // Client Proxy
        dispatchQueue.put(new Task(name, score));
    }
}
