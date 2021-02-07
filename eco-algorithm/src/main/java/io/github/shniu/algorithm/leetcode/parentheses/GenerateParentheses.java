package io.github.shniu.algorithm.leetcode.parentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/2/5 16
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        recur(0, 0, n, "", res);
        return res;
    }

    private void recur(int left, int right, int n, String s, List<String> res) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }

        if (left < n) recur(left + 1, right, n, s + "(", res);
        if (right < left) recur(left, right + 1, n, s + ")", res);
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        recur2(n, n, new char[2 * n], res);
        return res;
    }

    private void recur2(int left, int right, char[] selected, List<String> res) {
        if (right < left) return;
        if (left < 0 || right < 0) return;

        if (left == 0 && right == 0) {
            res.add(new String(selected));
            return;
        }

        selected[selected.length - left - right] = '(';
        recur2(left - 1, right, selected, res);

        selected[selected.length - left - right] = ')';
        recur2(left, right - 1, selected, res);
    }
}
