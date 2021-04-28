package io.github.shniu.algorithm.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 密码校验问题：本质是字符串匹配
 * @author niushaohan
 * @date 2021/4/28 11
 */
public class PasswordChecker {

    enum Res {
        NO,
        YES
    }

    public String check(String input, String output) {
        if (input == null || output == null) return Res.NO.name();
        if (input.length() > output.length()) return Res.NO.name();

        // 统计 input 中每个字符出现的次数
        Map<Character, Integer> timesPerCharOfInput = new HashMap<>();
        for (char c : input.toCharArray()) {
            Integer times = timesPerCharOfInput.getOrDefault(c, 0);
            timesPerCharOfInput.put(c, times + 1);
        }

        // 滑动窗口匹配
        Map<Character, Integer> temp = new HashMap<>(timesPerCharOfInput);
        int total = input.length();
        int start = 0, end = input.length() - 1;
        while (end < output.length()) {
            for (int i = start; i <= end; i++) {
                char ithOutputChar = output.charAt(i);
                Integer exist = temp.get(ithOutputChar);
                if (exist == null || exist - 1 < 0) {
                    break;
                }

                total -= 1;
                temp.put(ithOutputChar, exist - 1);
            }

            if (total == 0) return Res.YES.name();

            // reset
            start++;
            end++;
            total = input.length();
            temp = new HashMap<>(timesPerCharOfInput);
        }

        return Res.NO.name();
    }
}
