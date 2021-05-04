package io.github.shniu.algorithm.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author niushaohan
 * @date 2021/4/29 22
 */
public class Parenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, 0, 0, "", res);

        // System.out.println(res);
        return res;
    }

    void helper(int n, int left, int right, String str, List<String> res) {
        if (left == n && right == n) {
            res.add(str);
            return;
        }

        if (left < n) helper(n, left + 1, right, str + "(", res);
        if (right < left) helper(n, left, right + 1, str + ")", res);
    }

    public static void main(String[] args) {
        Parenthesis parenthesis = new Parenthesis();
        parenthesis.generateParenthesis(2);
    }
}
