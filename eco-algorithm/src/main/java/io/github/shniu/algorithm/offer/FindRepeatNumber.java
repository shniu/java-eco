package io.github.shniu.algorithm.offer;

/**
 * 剑指 Offer 03. 数组中重复的数字.
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 *
 * @author niushaohan
 * @date 2020/7/13 00
 */
public class FindRepeatNumber {
    // 思路1 排序后，判断相邻的值是否相等
    // 思路2 hashmap

    // 思路3 置换法，把数字 n 换到位置 n 上，如果出现冲突，就说明存在重复数字
    public int findRepeatNumber(int[] nums) {
        int tmp;
        for (int i = 0; i < nums.length; i++) {
            // 直到当次置换结束，再进入下一轮
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }

                tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }

        return -1;
    }
}
