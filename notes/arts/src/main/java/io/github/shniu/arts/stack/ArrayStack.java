package io.github.shniu.arts.stack;

/**
 * 使用数组来实现一个栈
 * 1. 添加一个功能让顺序栈支持动态扩容
 */
public class ArrayStack implements Stack {
    private static final int DEFAULT_CAPACITY = 16;

    // 存储栈中的元素
    private String[] items;

    // 栈元素的个数
    private int count;

    // 栈的容量
    private int capacity;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.capacity = capacity;

        items = new String[this.capacity];
    }

    @Override
    public boolean push(String item) {
        // push 之前先检查容量，容量不够时就自动扩容
        resize();

        items[count++] = item;
        return true;
    }

    private void resize() {
        if (!checkRemainCapacity()) {
            // 扩容
            int newCapacity = 2 * capacity;
            String[] newItems = new String[newCapacity];
            System.arraycopy(items, 0, newItems, 0, capacity);
            this.capacity = newCapacity;
            this.items = newItems;
        }
    }

    @Override
    public String pop() {
        if (checkNotEmpty()) {
            return items[--count];
        }
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {

    }

    private boolean checkRemainCapacity() {
        return count < capacity;
    }

    private boolean checkNotEmpty() {
        return count > 0;
    }
}
