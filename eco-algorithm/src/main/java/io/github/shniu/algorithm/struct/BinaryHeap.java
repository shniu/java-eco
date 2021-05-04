package io.github.shniu.algorithm.struct;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * 二叉堆的实现.
 * heap 数据结构：https://www.geeksforgeeks.org/heap-data-structure/
 * https://en.wikipedia.org/wiki/Heap_(data_structure)
 *
 * @author niushaohan
 * @date 2020/6/16 09
 */
public class BinaryHeap<T extends Comparable<T>> {
    // 先实现一个大顶堆

    // 存放堆元素
    private T[] heaps;

    // 当前堆的元素个数
    private int count;

    @SuppressWarnings("unchecked")
    public BinaryHeap(Class<T> type, int capacity) {
        heaps = (T[]) Array.newInstance(type, capacity);
    }

    // 向堆中添加元素: 从下到上堆化
    public void add(T item) {
        Objects.requireNonNull(item);

        // 扩容
        if (count + 1 == heaps.length) {
            heaps = Arrays.copyOf(heaps, 2 * heaps.length);
        }

        heaps[++count] = item;
        fixUp(count);
    }

    void fixUp(int k) {
        while (k > 1) {
            int j = k / 2;
            if (heaps[k].compareTo(heaps[j]) <= 0) {
                break;
            }
            swap(heaps, k, j);
            k = j;
        }
    }

    private void swap(T[] heaps, int i, int j) {
        T temp = heaps[i];
        heaps[i] = heaps[j];
        heaps[j] = temp;
    }

    // 查看堆顶元素
    public T peek() {
        if (count == 0) {
            return null;
        }

        return heaps[1];
    }

    // 取堆顶元素
    public T get() {
        if (count == 0) {
            return null;
        }

        T top = heaps[1];

        heaps[1] = heaps[count];
        heaps[count--] = null;

        fixDown(1);
        return top;
    }

    void fixDown(int k) {
        int j;
        while (true) {
            j = 2 * k;
            int maxPos = k;
            // 先跟左边比
            if (j <= count && heaps[k].compareTo(heaps[j]) < 0) maxPos = j;
            // 再跟右边比
            if (j + 1 <= count && heaps[maxPos].compareTo(heaps[j + 1]) < 0) maxPos = j + 1;
            // 如果当前节点比左右都大，就退出
            if (maxPos == k) break;

            // 交换并更新位置
            swap(heaps, k, maxPos);
            k = maxPos;
        }
    }

    public int size() {
        return count;
    }

    public void print() {
        for (T item : heaps) {
            System.out.println(item);
        }
        System.out.println("---- Over ----");
    }
}
