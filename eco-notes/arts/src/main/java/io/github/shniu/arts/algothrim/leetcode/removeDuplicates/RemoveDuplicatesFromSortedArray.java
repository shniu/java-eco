package io.github.shniu.arts.algothrim.leetcode.removeDuplicates;

/**
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/13 19
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        // corner case
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;

        int pos = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[pos++] = nums[i];
            }
        }
        return pos;
    }

    // snowball 解法 (滚雪球)
    public int removeDuplicates2(int[] nums) {
        // corner case
        if (nums == null) return 0;
        if (nums.length <= 1) return nums.length;

        int snowballSize = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                snowballSize++;
            } else {
                nums[i - snowballSize] = nums[i];
            }
        }
        return nums.length - snowballSize;
    }
}
