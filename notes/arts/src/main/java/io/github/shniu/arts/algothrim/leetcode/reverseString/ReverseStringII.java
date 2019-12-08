package io.github.shniu.arts.algothrim.leetcode.reverseString;

/**
 * https://leetcode-cn.com/problems/reverse-string-ii/
 * 541. 反转字符串 II
 */
public class ReverseStringII {

    public String reverseStr(String s, int k) {
        if (s.length() == 1) return s;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i += 2 * k) {
            int left = chars.length - i;
            if (left < k) {
                reverseStr(chars, i, chars.length - 1);
            } else {
                reverseStr(chars, i, i + k - 1);
            }
        }

        return new String(chars);
    }

    private void reverseStr(char[] chars, int p, int q) {
        while (p < q) {
            char temp = chars[p];
            chars[p++] = chars[q];
            chars[q--] = temp;
        }
    }
}
