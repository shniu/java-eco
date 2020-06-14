package io.github.shniu.arts.algothrim.leetcode.validParentheses;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 20. 有效的括号
 */
public class ValidParentheses {

    private static Map<Character, Character> dict = new HashMap<Character, Character>(){{
        put(')', '(');
        put('}', '{');
        put(']', '[');
    }};

    // 1. stack； 4ms
    public boolean isValid1(String s) {
        if (s == null || s.equals("")) return true;

        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (dict.containsValue(c)) {
                stack.addFirst(c);
            } else {
                if (stack.peekFirst() != dict.get(c)) {
                    return false;
                }
                stack.removeFirst();
            }
        }
        return stack.isEmpty();
    }

    // 2. 做些优化，要想更快，一个方向就是不使用标准库的里的类，尽量使用最原生的解决方式 0ms or 1ms  99%
    public boolean isValid2(String s) {
        char[] stack = new char[s.length()];
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') stack[idx++] = ')';
            else if (c == '[') stack[idx++] = ']';
            else if (c == '{') stack[idx++] = '}';
            else if (idx == 0 || stack[--idx] != c) return false;
        }
        return idx == 0;
    }

}
