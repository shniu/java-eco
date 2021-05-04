package io.github.shniu.algorithm.struct.heap;

/**
 * 假设实现一个大顶堆。
 *
 * @author niushaohan
 * @date 2021/4/29 14
 */
public class Heap {
    private int[] items;
    private int size;

    public Heap() {
        items = new int[16];
        size = 0;
    }

    // 添加新元素到堆
    public void add(int item) {
        int idx = size;
        items[idx] = item;

        int parentIdx;
        while (idx > 0) {
            parentIdx = idx / 2;
            if (items[parentIdx] < item) {
                int tmp = items[parentIdx];
                items[parentIdx] = item;
                items[idx] = tmp;
            }

            idx = parentIdx;
        }

        size++;
    }

    // 删除堆顶元素
    public int pollFirst() {
        int first = items[0];

        items[0] = items[size - 1];
        items[size - 1] = 0;
        size--;

        int parentIdx = 0;
        int maxPos;
        while (true) {
            maxPos = parentIdx;
            int leftChildIdx = 2 * parentIdx + 1;
            if (leftChildIdx < size && items[parentIdx] < items[leftChildIdx])
                maxPos = leftChildIdx;
            int rightChildIdx = leftChildIdx + 1;
            if (rightChildIdx < size && items[maxPos] < items[rightChildIdx])
                maxPos = rightChildIdx;

            if (maxPos == parentIdx) break;

            int tmp = items[parentIdx];
            items[parentIdx] = items[maxPos];
            items[maxPos] = tmp;

            parentIdx = maxPos;
        }

        return first;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(10);
        heap.add(4);
        heap.add(8);
        heap.add(12);
        heap.add(9);
        heap.add(3);
        heap.add(1);
        heap.add(30);
        System.out.println();

        int first = heap.pollFirst();
        System.out.println(first);
    }
}
