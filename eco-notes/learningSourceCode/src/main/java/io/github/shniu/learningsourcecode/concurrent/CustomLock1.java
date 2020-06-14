package io.github.shniu.learningsourcecode.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 自定义锁的实现，类似于 ReentrantLock
 */
public class CustomLock1 {
    // 队列中的节点
    static class Node {
        // 等待的线程
        Thread thread;
        // 等待队列的prev线程节点
        Node prev;
        // 等待队列的next线程节点
        Node next;

        public Node() {
        }

        public Node(Thread thread, Node prev) {
            this.thread = thread;
            this.prev = prev;
        }
    }

    private static Unsafe UNSAFE;

    static {
        try {
            // 获取 UNSAFE 实例
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);

            // state offset
            stateOffset = UNSAFE.objectFieldOffset(CustomLock1.class.getDeclaredField("state"));
            // tail offset
            tailOffset = UNSAFE.objectFieldOffset(CustomLock1.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // CAS 更新变量，更新成功的获取到锁
    private volatile int state = 0;
    private static long stateOffset;

    // 线程排队队列, 需要使用 volatile，因为要保证多线程间的可见性
    static final Node EMPTY = new Node();
    private volatile Node head;
    private volatile Node tail;
    private static long tailOffset;

    public CustomLock1() {
        head = tail = EMPTY;
    }

    /**
     * 锁的核心功能，加锁操作
     */
    public void lock() {
        // CAS 尝试加锁成功，直接返回
        if (compareAndSetState(0, 1)) {
            return;
        }

        // 没有更新成功，放入队列
        Node node = enqueue();  // 入队成功
        Node prev = node.prev;
        while (node.prev != head || !compareAndSetState(0, 1)) {
            // 没有获取到锁，阻塞
            UNSAFE.park(false, 0L);
        }
        head = node;
        // 设置为null，有助于gc，断开对象引用
        node.thread = null;
        node.prev = null;
        prev.next = null;
    }

    private Node enqueue() {
        // 自旋 CAS 更新尾节点
        for (; ; ) {
            Node currTail = tail;
            Node newNode = new Node(Thread.currentThread(), currTail);
            if (compareAndSetTail(currTail, newNode)) {
                currTail.next = newNode;
                return newNode;
            }
        }
    }

    /**
     * 锁的核心功能，解锁操作
     */
    public void unlock() {
        state = 0;
        Node next = head.next;
        // 下一个节点不为空，唤醒线程
        if (next != null) {
            UNSAFE.unpark(next.thread);
        }
    }

    private boolean compareAndSetState(int expect, int update) {
        return UNSAFE.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private boolean compareAndSetTail(Node except, Node update) {
        return UNSAFE.compareAndSwapObject(this, tailOffset, except, update);
    }
}
