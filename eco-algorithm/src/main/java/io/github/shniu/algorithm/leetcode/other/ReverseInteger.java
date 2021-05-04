package io.github.shniu.algorithm.leetcode.other;

/**
 * 7. https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author niushaohan
 * @date 2021/5/3 14
 */
public class ReverseInteger {

    public int reverse(int x) {
        int sign = 1;
        if (x < 0) sign = -1;

        int res = 0;
        x = Math.abs(x);
        while (x > 0) {
            int last = x % 10;

            // 溢出判断
            if (Integer.MAX_VALUE / 10 < res
                    || (Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < last)) {
                return 0;
            }

            res = res * 10 + last;
            x = x / 10;
        }

        return res * sign;
    }

}
