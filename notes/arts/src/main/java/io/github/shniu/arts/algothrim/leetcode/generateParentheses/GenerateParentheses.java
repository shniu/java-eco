package io.github.shniu.arts.algothrim.leetcode.generateParentheses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 22. 括号生成
 */
public class GenerateParentheses {
    // 1. 递归实现
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisHelper(0, 0, "", n, res);
        return res;
    }

    private void generateParenthesisHelper(int left, int right, String s, int n, List<String> res) {
        // terminator
        if (left == n && right == n) {
            res.add(s);
            return;
        }

        // process current logic & drill down
        if (left < n) generateParenthesisHelper(left + 1, right, s + "(", n, res);
        if (right < left) generateParenthesisHelper(left, right + 1, s + ")", n, res);
    }

    // 2. dfs 的另外一种实现
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, n, "", res);
        return res;
    }

    private void dfs(int leftRemain, int rightRemain, String s, List<String> res) {
        if (leftRemain == 0 && rightRemain == 0) {
            res.add(s);
            return;
        }

        if (leftRemain > 0) dfs(leftRemain - 1, rightRemain, s + "(", res);
        if (rightRemain > leftRemain) dfs(leftRemain, rightRemain - 1, s + ")", res);
    }

    // 3. 使用 bfs 的思想也可以实现
    public List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", 0, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                Objects.requireNonNull(curr);
                if (curr.left == n && curr.right == n) {
                    res.add(curr.s);
                    continue;
                }

                if (curr.left < n)
                    queue.offer(new Node(curr.s + "(", curr.left + 1, curr.right));
                if (curr.right < curr.left)
                    queue.offer(new Node(curr.s + ")", curr.left, curr.right + 1));
            }
        }

        return res;
    }

    class Node {
        String s;
        int left;
        int right;
        Node(String s, int left, int right) {
            this.s = s;
            this.left = left;
            this.right = right;
        }
    }
}
