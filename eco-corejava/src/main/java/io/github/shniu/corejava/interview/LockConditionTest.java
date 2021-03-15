package io.github.shniu.corejava.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionTest conditionTest1 = new ConditionTest(lock, condition, true);
        ConditionTest conditionTest2 = new ConditionTest(lock, condition, false);

        new Thread(conditionTest1).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(conditionTest2).start();
    }
}

class ConditionTest implements Runnable {
    private Lock lock;
    private Condition condition;
    private boolean wait;

    public ConditionTest(Lock lock, Condition condition, boolean wait) {
        this.lock = lock;
        this.condition = condition;
        this.wait = wait;
    }

    @Override
    public void run() {
        if (wait) conditionWait();
        if (!wait) conditionSignal();
    }

    public void conditionWait() {
        lock.lock();

        try {
            System.out.println("condition await");
            condition.await();
            System.out.println("exit");
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() {
        lock.lock();

        try {
            System.out.println("signal");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
