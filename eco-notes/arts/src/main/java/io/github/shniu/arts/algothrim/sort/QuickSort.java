package io.github.shniu.arts.algothrim.sort;

/**
 * 快速排序，分治思想，递归实现
 */
public class QuickSort implements Sortable {
    @Override
    public void sort(Comparable[] arr) {
        if (arr.length <= 1) return;
        sort(arr, 0, arr.length - 1);
    }

    private void sort(Comparable[] arr, int left, int right) {
        // terminator
        if (left >= right) return;

        int pos = partition(arr, left, right); // 分区位置
        // drill down
        sort(arr, left, pos - 1);  // 左边
        sort(arr, pos + 1, right);  // 右边
    }

    // 分区函数
    private int partition(Comparable[] arr, int left, int right) {
        Comparable pivot = arr[right];
        int partitionPos = left;
        for (int i = left; i < right; i++) {
            if (less(arr[i], pivot)) {  // arr[i] < pivot
                // swap arr[partitionPos] & arr[i]
                Comparable t = arr[partitionPos];
                arr[partitionPos++] = arr[i];
                arr[i] = t;
            }
        }
        arr[right] = arr[partitionPos];
        arr[partitionPos] = pivot;
        return partitionPos;
    }
}
