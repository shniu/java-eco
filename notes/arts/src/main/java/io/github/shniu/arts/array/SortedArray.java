package io.github.shniu.arts.array;

import java.util.Arrays;

/**
 * 实现一个大小固定的有序数组，并支持动态增删改操作
 *
 * @author shniu
 * @date 2019/09/26 17:03:13
 */
public class SortedArray {

    private int capacity;
    private int[] elements;
    private int size;

    public SortedArray(int initialCapacity) {
        capacity = checkInitialCapacity(initialCapacity);
        elements = new int[capacity];
    }

    private int checkInitialCapacity(int initialCapacity) {
        if (initialCapacity <= 0) {
            return 16;
        }
        return initialCapacity;
    }

    /**
     * 添加数据到数组中，需要保持有序
     */
    public boolean add(int element) {
        if (isFull()) {
            return false;
        }

        int pos = 0;
        for (int i = size; i > 0; i--) {
            if (element < elements[i - 1]) {
                elements[i] = elements[i - 1];
            } else {
                pos = i;
                break;
            }
        }
        elements[pos] = element;
        size++;

        return true;
    }

    private boolean isFull() {
        return size >= capacity;
    }

    /**
     * 删除数组中的数据，需要保持有序
     */
    public void delete(int index) {
        for (int i = index; i < size; i++) {
            if (i == capacity - 1) {
                elements[i] = 0;
                break;
            }
            elements[i] = elements[i + 1];
        }
        size--;
    }

    public String toString() {
        return Arrays.toString(elements);
    }
}
