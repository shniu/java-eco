package io.github.shniu.arts.algothrim.sort;

/**
 * 在 O(n) 的时间复杂度下求解第K大元素
 * 利用快排思想
 */
public class KthSmallest {

    /**
     * 在数组中找第 K 大元素
     *
     * @param arr 数组
     * @param k   第 K 大
     * @return 对应的元素
     */
    public int findKth(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            throw new RuntimeException("数组元素数量不足 k 个");
        }

        int p = partition(arr, 0, arr.length - 1);
        while (p + 1 != k) {
            if (p + 1 < k) {
                p = partition(arr, p + 1, arr.length - 1);
            } else {
                p = partition(arr, 0, p - 1);
            }
        }

        return arr[p];
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int pos = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, pos, j);
                pos++;
            }
        }
        swap(arr, pos, right);
        return pos;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) return;

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
