package io.github.shniu.arts.algothrim.leetcode;

/**
 * @author shniu
 * @date 2019/10/31 14:29:19
 */
public class LeetCode69 {
    public int mySqrt(int x) {
        if (x == 0) return 0;

        int left = 1, right = (x >> 1);
        while (left < right) {
            int mid = left + ((right - left) >> 1);

            int sqrt = x / mid;
            if (mid == sqrt) {
                return mid;
            } else if (mid > sqrt) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1)) return mid;
                left = mid + 1;
            }
        }

        return left;
    }
}
