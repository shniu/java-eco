package io.github.shniu.arts.training.week02;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// https://leetcode-cn.com/problems/valid-anagram/
public class LeetCode_242_716 {

    // 2.1 使用 HashMap
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            Integer freqInS = charFrequency.getOrDefault(s.charAt(i), 0);
            charFrequency.put(s.charAt(i), freqInS + 1);

            Integer freqInT = charFrequency.getOrDefault(t.charAt(i), 0);
            charFrequency.put(t.charAt(i), freqInT - 1);
        }

//        for (Integer value : charFrequency.values()) {
//            if (value != 0) {
//                return false;
//            }
//        }
        for (Map.Entry<Character, Integer> characterIntegerEntry : charFrequency.entrySet()) {
            if (characterIntegerEntry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }
}
