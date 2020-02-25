package io.github.shniu.arts.algothrim.leetcode.nextPermutation;

/**
 * 31. 下一个序列
 * https://leetcode-cn.com/problems/next-permutation/
 */
public class NextPermutation {

    // 更偏重逻辑思维，找到规律就好解决了
    public void nextPermutation(int[] nums) {
        // 1. 从后向前找到 nums[i] < nums[j] 的 i和j
        int i = nums.length - 2, j = nums.length - 1;
        while (i >= 0 && nums[i] >= nums[j]) {
            i--;
            j--;
        }

        // 2. 找到j...end 之间可以和i互换的位置
        if (i >= 0) {
            int k = nums.length - 1;
            while (nums[i] >= nums[k]) {
                k--;
            }
            // swap
            swap(nums, i, k);
        }

        // 3. j...end 升序排
        int end = nums.length - 1;
        while (j < end) {
            swap(nums, j++, end--);
        }
    }

    private void swap(int[] nums, int p, int q) {
        if (p >= 0 && p < q && q <= nums.length - 1) {
            int tmp = nums[p];
            nums[p] = nums[q];
            nums[q] = tmp;
        }
    }
}
