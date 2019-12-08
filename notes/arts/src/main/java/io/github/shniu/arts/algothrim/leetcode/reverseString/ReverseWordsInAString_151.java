package io.github.shniu.arts.algothrim.leetcode.reverseString;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 * 151. 翻转字符串里的单词
 */
public class ReverseWordsInAString_151 {

    public String reverseWords1(String s) {
        // reverse i...j
        return join(reverse(trim(s)));
    }

    private String trim(String s) {
        if (s.equals("")) return "";
        int i = 0, j = s.length() - 1;
        while (i <= j && (s.charAt(i) == ' ' || s.charAt(j) == ' ')) {
            if (s.charAt(i) == ' ') i++;
            if (s.charAt(j) == ' ') j--;
        }

        if (i > j) return "";
        return s.substring(i, j + 1);
    }

    private String[] reverse(String s) {
        // split, 使用正则 ` +` 降低了执行速度
        String[] words = s.split(" +");

        int p = 0, q = words.length - 1;
        while (p < q) {
            String tmp = words[p];
            words[p++] = words[q];
            words[q--] = tmp;
        }

        return words;
    }

    private String join(String[] words) {
        if (words.length == 1) return words[0];

        // join
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.equals("")) {
                continue;
            }
            sb.append(w);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    //
    public String reverseWords2(String s) {
        if (s.equals("")) return "";

        int i = 0, j = s.length() - 1;
        while (i <= j && (s.charAt(i) == ' ' || s.charAt(j) == ' ')) {
            if (s.charAt(i) == ' ') i++;
            if (s.charAt(j) == ' ') j--;
        }

        if (i > j) return "";

        String[] words = s.substring(i, j + 1).split(" +");

        int p = 0, q = words.length - 1;
        while (p < q) {
            String tmp = words[p];
            words[p++] = words[q];
            words[q--] = tmp;
        }

        return String.join(" ", words);
    }
}
