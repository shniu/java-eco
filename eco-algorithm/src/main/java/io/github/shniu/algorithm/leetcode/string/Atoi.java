package io.github.shniu.algorithm.leetcode.string;

/**
 * @author niushaohan
 * @date 2021/4/16 18
 */
public class Atoi {

    // 10000000000000000000000000000000 - 1 -> 01111111111111111111111111111111
    // 10000000000000000000000000000000

    public static int MIN = 1 << 31;
    public static int MAX = (1 << 31) - 1;

    public int strToInt(String str) {
        // 最终结果
        int res = 0;

        // 是不是负数
        boolean negative = false;
        // 有没有遇到过数字
        boolean meetNumber = false;

        int len = str.length();
        int i = 0;

        while (i < len) {
            char currentChar = str.charAt(i);
            i++;

            // 可以忽略的字符
            if (isBlank(currentChar)) {
                if (meetNumber) break;
            } else if (isSignChar(currentChar)) {
                if (meetNumber) break;

                if (i >= len || !isNumber(str.charAt(i))) {
                    break;
                }

                if (currentChar == '-') {
                    negative = true;
                }
            } else if (isNumber(currentChar)) {
                if (res == 0 && currentChar == '0') {
                    if (i >= len || !isNumber(str.charAt(i))) {
                        break;
                    }
                }

                if (res != 0 && currentChar > '0') {
                    meetNumber = true;
                }

                int digit = Integer.parseInt(String.valueOf(currentChar));

                // 溢出判断
                if (res > Integer.MAX_VALUE / 10
                        || Integer.MAX_VALUE / 10 == res && Integer.MAX_VALUE % 10 < digit) {
                    return negative ? MIN : MAX;
                }

                res = res * 10 + digit;
            } else {
                break;
            }
        }

        if (negative && res != 0) {
            res *= -1;
        }

        return res;
    }

    boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    boolean isSignChar(char c) {
        return c == '-' || c == '+';
    }

    boolean isBlank(char c) {
        return c == ' ';
    }
}
