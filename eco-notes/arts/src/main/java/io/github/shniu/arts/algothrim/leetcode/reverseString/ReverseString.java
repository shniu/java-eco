package io.github.shniu.arts.algothrim.leetcode.reverseString;

import java.util.Collections;

/**
 * https://leetcode-cn.com/problems/reverse-string/
 * 344. 反转字符串
 */
public class ReverseString {

    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;

        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
