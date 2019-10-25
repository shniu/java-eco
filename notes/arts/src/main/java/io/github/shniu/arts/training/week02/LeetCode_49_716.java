package io.github.shniu.arts.training.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode-cn.com/problems/group-anagrams
public class LeetCode_49_716 {

    // 1. hash表存储排序字符串的列表
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> resMap = new HashMap<>();

        for (String str : strs) {
            // sort str
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            List<String> strList = resMap.getOrDefault(sortedStr, new ArrayList<>());
            strList.add(str);
            resMap.put(sortedStr, strList);

            // resMap.computeIfAbsent(sortedStr, k -> new ArrayList<>());
            // resMap.get(sortedStr).add(str);
        }

        return new ArrayList<>(resMap.values());
    }

    // 2. 排序的时间复杂度较高，可以考虑使用26个bucket来存放每个单词的频率
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> resMap = new HashMap<>();
        for (String s : strs) {
            int[] alphabet = new int[26];
            for (char c : s.toCharArray()) {
                alphabet[c - 'a']++;
            }

            // String key = Arrays.toString(alphabet);
            StringBuilder sb = new StringBuilder();
            for (int freq : alphabet) {
                sb.append('@');
                sb.append(freq);
            }
            String key = sb.toString();

            if (!resMap.containsKey(key)) {
                resMap.put(key, new ArrayList<>());
            }
            resMap.get(key).add(s);
        }

        return new ArrayList<>(resMap.values());
    }
}
