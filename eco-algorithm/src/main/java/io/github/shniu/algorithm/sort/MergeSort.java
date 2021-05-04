package io.github.shniu.algorithm.sort;

/**
 * @author niushaohan
 * @date 2021/4/29 10
 */
public class MergeSort {

    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int low, int high) {
        if (low >= high) return;

        int middle = low + ((high - low) / 2);

        mergeSort(nums, low, middle);
        mergeSort(nums, middle + 1, high);

        merge(nums, low, middle, high);
    }

    // 合并两个有序数组
    private void merge(int[] nums, int low, int middle, int high) {
        int i = low, j = middle + 1, k = 0;

        int[] tmp = new int[high - low + 1];

        while (i <= middle && j <= high) {
            if (nums[i] < nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }

        while (i <= middle) tmp[k++] = nums[i++];
        while (j <= high) tmp[k++] = nums[j++];

        System.arraycopy(tmp, 0, nums, low, tmp.length);
    }
}
