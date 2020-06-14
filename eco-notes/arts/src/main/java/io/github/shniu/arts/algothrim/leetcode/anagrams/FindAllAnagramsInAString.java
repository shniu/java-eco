package io.github.shniu.arts.algothrim.leetcode.anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * 438. 找到字符串中所有字母异位词
 */
public class FindAllAnagramsInAString {
    // 1. 暴力法，复杂度太高，就不实现了

    // 2. 滑动窗口法
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;

        // 左右指针
        int left = 0, right = 0, total = p.length();

        int[] needs = new int[26];
        int[] window = new int[26];
        for (char c : p.toCharArray()) {
            needs[c - 'a']++;
        }

        while (right < s.length()) {
            char r = s.charAt(right);
            // p 中存在字符 r
            if (needs[r - 'a'] > 0) {
                window[r - 'a']++;
                if (window[r - 'a'] <= needs[r - 'a']) {
                    total--;
                }
            }

            while (total == 0) {
                if (right - left + 1 == p.length()) {
                    res.add(left);
                }

                char l = s.charAt(left);
                if (needs[l - 'a'] > 0) {
                    window[l - 'a']--;
                    if (window[l - 'a'] < needs[l - 'a']) {
                        total++;
                    }
                }

                left++;
            }

            right++;
        }

        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (p.length() > s.length()) return result;

        // 统计在模式串 p 中的字符出现次数
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 字符数
        int counter = map.size();
        // 双指针
        int begin = 0, end = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) counter--;
            }
            end++;

            while (counter == 0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }

                // 判断串口的宽度是不是模式串的长度，如果是就说明这个子串和模式串是异位词
                if (end - begin == p.length()) {
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }

    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length() || s.equals(t)) return false;

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
            if (freq[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
