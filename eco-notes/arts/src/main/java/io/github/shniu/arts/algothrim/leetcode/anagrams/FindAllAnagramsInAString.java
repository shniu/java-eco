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

        // 这个是目标字符串的每个字符出现的次数统计，是固定的
        int[] needs = new int[26];
        // 这个是窗口中的每个字符出现的次数统计
        int[] window = new int[26];

        // 目的就是找到 needs == window 的情况

        // 初始化所有 needs
        for (char c : p.toCharArray()) {
            needs[c - 'a']++;
        }

        // 右指针移动到字符串末尾结束
        while (right < s.length()) {
            // 当前字符
            char r = s.charAt(right);
            // p 中存在字符 r，就在 window 中加入这个字符
            if (needs[r - 'a'] > 0) {
                window[r - 'a']++; // 字符频次 +1
                if (window[r - 'a'] <= needs[r - 'a']) {  // 找到了一个符合的字符，total 数量-1
                    total--;
                }
            }

            while (total == 0) {  // total == 0 时说明，窗口中出现了满足 needs 的字符频率，但是不一定存在这个字串，需要移动 left
                if (right - left + 1 == p.length()) { // 这里就说明已经有合适的字串出现了
                    res.add(left);
                }

                char l = s.charAt(left);  // left 指针的字符在needs中，就更新 window
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
