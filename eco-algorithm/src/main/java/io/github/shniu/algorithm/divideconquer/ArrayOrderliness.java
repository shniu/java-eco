package io.github.shniu.algorithm.divideconquer;

/**
 * 求解给定数组的有序度.
 *
 * @author niushaohan
 * @date 2021/4/13 09
 */
public class ArrayOrderliness {
    private int num = 0;

    // 方法1 暴力求解，挨个和后面的数字比较，复杂度较高 O(n^2)
    // 方法2 分治思想，逐步求解，类似于归并排序
    public int count(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        count(array, 0, array.length - 1);
        return num;
    }

    private void count(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        count(array, low, mid);
        count(array, mid + 1, high);
        mergeCount(array, low, mid, high);
    }

    private void mergeCount(int[] arr, int low, int mid, int high) {
        int len = high - low + 1;
        int i = low, j = mid + 1, k = 0;
        int[] tmp = new int[len];

        while (i <= mid && j <= high) {
            if (arr[i] > arr[j]) {
                num += (mid - i + 1);
                tmp[k++] = arr[j++];
            } else {
                tmp[k++] = arr[i++];
            }
        }

        while (i <= mid) {
            tmp[k++] = arr[i++];
        }

        while (j <= high) {
            tmp[k++] = arr[j++];
        }

//        for (i = 0; i <= high - low; ++i) {
//            arr[low + i] = tmp[i];
//        }
        System.arraycopy(tmp, 0, arr, low, len);
    }

}
