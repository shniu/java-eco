package io.github.shniu.arts.algothrim.leetcode.reverseString;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 * 557. 反转字符串中的单词 III
 */
public class ReverseWordsInAStringIII_557 {

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        String[] res = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            res[i] = reverse(words[i]);
        }
        return String.join(" ", res);
    }

    private String reverse(String s) {
        char[] chars = s.toCharArray();
        int p = 0, q = chars.length - 1;
        while (p < q) {
            char tmp = chars[p];
            chars[p++] = chars[q];
            chars[q--] = tmp;
        }

        return new String(chars);
    }


}
