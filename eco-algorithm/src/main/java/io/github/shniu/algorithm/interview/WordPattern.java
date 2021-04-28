package io.github.shniu.algorithm.interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 简单的模式匹配问题
 * https://www.codeleading.com/article/58723662565/
 *
 * aab -> 北京 北京 上海   结果 true
 * abbc -> 北京 北京 上海 杭州  结果 false
 *
 * @author niushaohan
 * @date 2021/4/28 09
 */
public class WordPattern {

    public boolean match(String targetStr, String pattern) {
        if (targetStr == null || pattern == null) {
            return false;
        }

        String[] words = targetStr.split(" ");

        if (words.length != pattern.length()) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < pattern.length(); i++) {
            char patternItem = pattern.charAt(i);
            String comparedWord = words[i];

            String existWord = map.get(patternItem);
            if (existWord == null) {
//                if (map.containsValue(comparedWord)) {
//                    // System.out.println("==" + targetStr + pattern);
//                    return false;
//                }

                if (visited.contains(comparedWord)) {
                    return false;
                }

                map.put(patternItem, comparedWord);
                visited.add(comparedWord);
            } else {
                if (!comparedWord.equals(existWord)) {
                    return false;
                }
            }
        }

        return true;
    }
}
