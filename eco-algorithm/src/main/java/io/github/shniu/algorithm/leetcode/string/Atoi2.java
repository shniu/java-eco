package io.github.shniu.algorithm.leetcode.string;

/**
 * @author niushaohan
 * @date 2021/4/16 18
 */
public class Atoi2 {
    public static int MIN = 1 << 31;
    public static int MAX = (1 << 31) - 1;

    public int strToInt(String str) {
        // 空字符
        if (str == null || str.length() == 0) return 0;

        int index = 0, sign = 1, total = 0;

        // 去除空格
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }

        // 取符号
        if (index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;

            // 判断溢出
            if (total > Integer.MAX_VALUE / 10 ||
                    Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = 10 * total + digit;
            index++;
        }

        return sign == 1 ? total : -total;
    }
}
