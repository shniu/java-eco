package io.github.shniu.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有A，B，C三个线程，A线程输出A，B线程输出B，C线程输出C，要求，同时启动三个线程，按顺序输出ABC，循环10次。
 */
public class PrintABC {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int count = 0;

    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();
        new Thread(printABC.new PrintA()).start();
        new Thread(printABC.new PrintB()).start();
        new Thread(printABC.new PrintC()).start();
    }

    class PrintA implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (count < 15) {
                    lock.lock();
                    System.out.print("A");
                    try {
                        conditionB.signal();
                        conditionA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.exit(0);
                }
            }
        }
    }

    class PrintB implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (count < 15) {
                    lock.lock();
                    System.out.print("B");
                    try {
                        conditionC.signal();
                        conditionB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.exit(0);
                }
            }
        }
    }

    class PrintC implements Runnable {

        @Override
        public void run() {
            while (true) {
                if (count < 15) {
                    lock.lock();
                    System.out.println("C" + count);
                    count++;
                    try {
                        conditionA.signal();
                        conditionC.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.exit(0);
                }

            }
        }
    }
}
