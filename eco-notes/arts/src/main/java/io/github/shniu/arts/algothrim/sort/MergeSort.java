package io.github.shniu.arts.algothrim.sort;

/**
 * 归并排序, 使用了分治思想，递归实现
 */
public class MergeSort implements Sortable {

    @Override
    public void sort(Comparable[] arr) {
        if (arr.length <= 1) return;
        sort(arr, 0, arr.length - 1);
    }

    private void sort(Comparable[] arr, int lo, int hi) {
        // terminator
        if (lo >= hi) {
            return;
        }

        int mid = lo + ((hi - lo) >> 1);  // 分成两半
        sort(arr, lo, mid);  // 排序左边
        sort(arr, mid + 1, hi);  // 排序右边
        merge(arr, lo, mid, hi);  // 合并结果
    }

    // 将两个有序的数组合并为一个有序数组
    private void merge(Comparable[] arr, int lo, int mid, int hi) {
        int len = hi - lo + 1;
        // 新申请一个数组
        Comparable[] temp = new Comparable[len];
        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) {
            // arr[i] > arr[j], 复制后面的数据到temp
            if (greater(arr[i], arr[j])) {
                temp[k++] = arr[j++];
            } else {
                temp[k++] = arr[i++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= hi) temp[k++] = arr[j++];

        // copy
        System.arraycopy(temp, 0, arr, lo, len);
    }
}
