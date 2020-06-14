package io.github.shniu.arts.algothrim.leetcode.reverseOnlyLetters;

/**
 * https://leetcode-cn.com/problems/reverse-only-letters/
 * 917. 仅仅反转字母
 */
public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int p = 0, q = chars.length - 1;
        while (p < q) {
            while (p < chars.length && !isLetter(chars[p])) p++;
            while (q >= 0 && !isLetter(chars[q])) q--;
            if (p >= q) break;

            char tmp = chars[p];
            chars[p++] = chars[q];
            chars[q--] = tmp;
        }

        return new String(chars);
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
