package io.github.shniu.arts.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class LeetCode22 {

    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        _generate(0, 0, n, "", res);
        return res;
    }

    private void _generate(int left, int right, int n, String s, List<String> res) {
        // terminator
        if (left == n && right == n) {
            res.add(s);
            return;
        }

        // process current logic: add left or add right

        // drill down
        if (left < n) _generate(left + 1, right, n, s + "(", res);
        if (left > right) _generate(left, right + 1, n, s + ")", res);
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        dfs("", n, n, res);
        return res;
    }
    // 不同的实现方式，但核心思想是一致的
    private void dfs(String s, int leftRemain, int rightRemain, List<String> res) {
        // terminator
        if (leftRemain == 0 && rightRemain == 0) {
            res.add(s);
            return;
        }

        // process current logic: add left or add right
        // drill down
        if (leftRemain > 0) dfs(s + "(", leftRemain - 1, rightRemain, res);
        if (rightRemain > leftRemain) dfs(s + ")", leftRemain, rightRemain - 1, res);
    }

    // -----
    // 使用广度优先搜索
    public List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", 0, 0));
        int m = 2 * n;
        while (m > 0) {
            int size = queue.size();
            while (size-- > 0) {
                Node curr = queue.poll();
                if (Objects.requireNonNull(curr).left < n)
                    queue.offer(new Node(curr.s + "(", curr.left + 1, curr.right));
                if (curr.left > curr.right)
                    queue.offer(new Node(curr.s + ")", curr.left, curr.right + 1));
            }
            m--;
        }

        while (!queue.isEmpty()) {
            res.add(queue.poll().s);
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
