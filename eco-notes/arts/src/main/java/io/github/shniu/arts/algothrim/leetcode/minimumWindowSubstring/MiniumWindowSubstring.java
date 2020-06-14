package io.github.shniu.arts.algothrim.leetcode.minimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/minimum-window-substring/
 * 76. 最小覆盖子串
 */
public class MiniumWindowSubstring {

    // 1. 滑动窗口
    public String minWindow1(String s, String t) {
        if (s == null || s.length() == 0 || s.length() < t.length()) return "";

        Map<Character, Integer> charDict = new HashMap<>();
        for (char c : t.toCharArray())
            charDict.put(c, charDict.getOrDefault(c, 0) + 1);
        int counter = charDict.size();

        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        int head = 0;

        while (right < s.length()) {
            char r = s.charAt(right);
            if (charDict.containsKey(r)) {
                charDict.put(r, charDict.get(r) - 1);
                if (charDict.get(r) == 0) counter--;
            }
            right++;

            while (counter == 0) {
                char l = s.charAt(left);
                if (charDict.containsKey(l)) {
                    charDict.put(l, charDict.get(l) + 1);
                    if (charDict.get(l) > 0) counter++;
                }

                if (right - left < len) {
                    len = right - left;
                    head = left;
                }
                left++;
            }
        }

        if (len == Integer.MAX_VALUE) return "";
        return s.substring(head, head + len);
    }

    // 2ms 的写法
    public String minWindow2(String s, String t) {
        int[] counts = new int[128];
        for (char c : t.toCharArray()) {
            counts[c]++;
        }
        char[] sChar = s.toCharArray();
        int minLeft = 0, minRight = 0, remain = t.length();
        for (int left = 0, index = 0; left < sChar.length; ) {
            // 先走右边，再走左边，算完最大值，左边右移一位
            while (remain > 0 && index < sChar.length) {
                char c = sChar[index++];
                remain = --counts[c] >= 0 ? remain - 1 : remain;
            }
            while (left < index && counts[sChar[left]] < 0) {
                counts[sChar[left++]]++;
            }
            if (remain == 0 && (index - left < minRight - minLeft || minLeft == minRight)) {
                minLeft = left;
                minRight = index;
            }
            if (left < sChar.length) {
                counts[sChar[left++]]++;
                remain++;
            }
        }
        return minLeft >= minRight ? "" : s.substring(minLeft, minRight);
    }
}
