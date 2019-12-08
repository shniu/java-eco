package io.github.shniu.arts.algothrim.leetcode.firstUniqChar;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 * 387. 字符串中的第一个唯一字符
 */
public class FirstUniqChar {

    // 1. 暴力搜索
    public int firstUniqChar1(String s) {
        // i 遍历字符串中的每个字符
        //    j 查找i之后是否出现该字符
        return 0;
    }

    // 2. 计数法
    public int firstUniqChar2(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 3
    public int firstUniqChar3(String s) {
        int[] bucket = new int[26];
        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (bucket[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    // 4
    public int firstUniqChar4(String s) {
        int res = Integer.MAX_VALUE;
        for (char i = 'a'; i <= 'z'; i++) {
            int index = s.indexOf(i);
            if (index != -1 && index == s.lastIndexOf(i)) {
                res = Math.min(res, index);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
