package io.github.shniu.arts.algothrim.leetcode.letterCombinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 17. 电话号码的字母组合
 */
public class LetterCombinations {
    // 对这个的处理还可以更高效一点，使用数组
    private static final Map<Character, String> dict = new HashMap<Character, String>() {{
        put('1', "");
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    // 1. 递归 backtrace
    public List<String> letterCombinations1(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        combination(digits, 0, "", res);
        return res;
    }

    private void combination(String digits, int pos, String s, List<String> res) {
        // terminator
        if (pos == digits.length()) {
            res.add(s);
            return;
        }

        String letters = dict.get(digits.charAt(pos));
        for (Character c : letters.toCharArray()) {
            // drill down
            combination(digits, pos + 1, s + c, res);
        }
    }

    // 2. bfs
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) return res;

        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.addLast("");
        while (res.peekFirst().length() != digits.length()) {
            String curr = res.removeFirst();
            String letters = mapping[digits.charAt(curr.length()) - '0'];
            for (char c : letters.toCharArray()) {
                res.addLast(curr + c);
            }
        }

        return res;
    }
}
