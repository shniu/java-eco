package io.github.shniu.algorithm.sort;

/**
 * @author niushaohan
 * @date 2021/4/29 10
 */
public class FindKthMaximumElement {

    // 在数组中找到第 k 大的元素
    public int kthMaximum(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }

        int p = partition(nums, 0, nums.length - 1);

        while (p + 1 != k) {
            if (p + 1 < k) {
                p = partition(nums, p + 1, nums.length - 1);
            } else {
                p = partition(nums, 0, p - 1);
            }
        }

        return nums[p];
    }

    private int partition(int[] nums, int low, int high) {
        // 一般取最后一位作为分割点
        int pivot = nums[high];

        // 双指针
        int partitionPos = low, i = low;

        while (i < high) {
            if (pivot <= nums[i]) {
                if (partitionPos != i) {
                    int tmp = nums[i];
                    nums[i] = nums[partitionPos];
                    nums[partitionPos] = tmp;
                }
                partitionPos++;
            }

            i++;
        }

        nums[high] = nums[partitionPos];
        nums[partitionPos] = pivot;

        return partitionPos;
    }
}
