package io.github.shniu.arts.algothrim.leetcode;

/**
 * @author shniu
 * @date 2019/09/29 18:31:30
 */
public class Solution41 {

    public int firstMissingPositive(int[] nums) {
        int length = nums.length;

        // 遍历数组，使用桶排序的思想
        // 数字 i 上应该放在 i-1 的位置上
        // 如果超过了 length 或者小于等于0，就忽略
        // 一直把所有满足情况的数字都交换到正确的位置上
        for (int i = 0; i < length; i++) {
            // 把 nums[i] 交换到应有的位置上
            while (nums[i] > 0 && nums[i] <= length && nums[i] != nums[nums[i] - 1]) {
                // swap
                swap(nums, i, nums[i] - 1);
            }
        }

        // 遍历，找到不符合要求的位置，就是要找的结果
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return length + 1;
    }

    private void swap(int[] nums, int from, int to) {
        if (from == to) return;

        int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
    }

    public int firstMissingPositive2(int[] nums) {
        int length = nums.length;
        // 使用一个新的数组来记录在 1 ~ length 之间的数字出现情况， i  的位置为1 表示 i + 1 出现了
        int[] arr = new int[length];
        for (int num : nums) {
            if (num > 0 && num <= length) {
                arr[num - 1] = 1;
            }
        }

        int i = 0;
        for (; i < length; i++) {
            if (arr[i] != 1) {
                return i + 1;
            }
        }
        return i + 1;
    }
}
