package io.github.shniu.arts.algothrim.leetcode.longestCommonPrefix;

/**
 * https://leetcode-cn.com/problems/longest-common-prefix
 * 14. 最长公共前缀
 */
public class LongestCommonPrefix {

    // 1
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        char[] first = strs[0].toCharArray();
        int end = first.length;
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < end && j < strs[i].length(); j++) {
                if (strs[i].charAt(j) != first[j]) {
                    break;
                }
            }

            end = j;
            if (end == 0) {
                return "";
            }
        }

        char[] res = new char[end];
        System.arraycopy(first, 0, res, 0, end);
        return new String(res);
    }

    // 2
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (String str : strs) {
            while (str.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // 3
    public String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            // 扫描后面的所有字符串
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    // 4. Trie
}
