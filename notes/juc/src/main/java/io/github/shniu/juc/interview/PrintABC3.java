package io.github.shniu.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印A B C
 */
public class PrintABC3 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();

        new Thread(new PrintA(lock, a, b)).start();
        new Thread(new PrintB(lock, b, c)).start();
        new Thread(new PrintC(lock, c, a)).start();
    }
}

abstract class Print implements Runnable {
    private Lock lock;
    private Condition self;
    private Condition next;

    static int count = 1;
    private static int MAX = 100;

    public Print(Lock lock, Condition self, Condition next) {
        this.lock = lock;
        this.self = self;
        this.next = next;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (count > MAX) {
                self.signalAll();
                next.signalAll();
                lock.unlock();
                return;
            }

            print();

            next.signal();
            try {
                self.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    abstract void print();
}

class PrintA extends Print {

    PrintA(Lock lock, Condition self, Condition next) {
        super(lock, self, next);
    }

    @Override
    void print() {
        System.out.print(count + ": A");
    }
}

class PrintB extends Print {

    PrintB(Lock lock, Condition self, Condition next) {
        super(lock, self, next);
    }

    @Override
    void print() {
        System.out.print("B");
    }
}

class PrintC extends Print {

    PrintC(Lock lock, Condition self, Condition next) {
        super(lock, self, next);
    }

    @Override
    void print() {
        System.out.println("C");
        count++;
    }
}
