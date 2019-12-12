package io.github.shniu.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用多线程去处理"abc"，"def"，“ghi”这个三个字符串，让它们以"adg"，"beh"，“cfi”这种形式输出。
 * 和启动三个线程，依次输出 A B C 是类似的
 * 使用等待-通知机制实现
 */
public class PrintByOrder2 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();

        new Thread(new OrderOne(lock, condition1, condition2)).start();
        new Thread(new OrderTwo(lock, condition2, condition3)).start();
        new Thread(new OrderThree(lock, condition3, condition1)).start();
    }
}

abstract class AbstractPrintOrder implements Runnable {
    private Lock lock;
    private Condition self;
    private Condition next;

    static int pos = 0;
    private static int max = 3;

    public AbstractPrintOrder(Lock lock, Condition self, Condition next) {
        this.lock = lock;
        this.self = self;
        this.next = next;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (pos >= max) {
                self.signal();
                next.signal();
                lock.unlock();
                return;
            }

            print();

            next.signal();
            try {
                self.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    abstract void print();
    abstract char getContent(int pos);
}

class OrderOne extends AbstractPrintOrder {

    public OrderOne(Lock lock, Condition self, Condition next) {
        super(lock, self, next);
    }

    @Override
    void print() {
        System.out.print(getContent(pos));
    }

    @Override
    char getContent(int pos) {
        return "abc".charAt(pos);
    }
}

class OrderTwo extends AbstractPrintOrder {

    public OrderTwo(Lock lock, Condition self, Condition next) {
        super(lock, self, next);
    }

    @Override
    void print() {
        System.out.print(getContent(pos));
    }

    @Override
    char getContent(int pos) {
        return "def".charAt(pos);
    }
}

class OrderThree extends AbstractPrintOrder {

    public OrderThree(Lock lock, Condition self, Condition next) {
        super(lock, self, next);
    }

    @Override
    void print() {
        System.out.println(getContent(pos));
        pos++;
    }

    @Override
    char getContent(int pos) {
        return "ghi".charAt(pos);
    }
}
