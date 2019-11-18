package io.github.shniu.arts.leetcode;

/**
 * @author shniu
 * @date 2019/10/31 18:12:04
 *
 * 在旋转后的有序数组中找最小元素，也就是找旋转点
 */
public class SearchMinElementInRotatedSortedArray {
    /**
     * 查找半有序数组的最小元素的索引（同理就是找无序的地方）
     */
    public int findMinElementIndex(int[] nums) {
        int low = 0, high = nums.length - 1;
        int lastElement = nums[high];
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            System.out.println(mid);
            if (nums[mid] < lastElement) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}
