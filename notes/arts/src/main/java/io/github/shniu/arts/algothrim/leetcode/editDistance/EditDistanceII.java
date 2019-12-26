package io.github.shniu.arts.algothrim.leetcode.editDistance;

import java.util.LinkedList;
import java.util.List;

/**
 * 对编辑距离问题的扩展
 * 编辑距离问题中求出了编辑距离，但是没有求出需要做的操作都是什么
 * 返回编辑路径
 * https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/bian-ji-ju-li
 */
public class EditDistanceII {
    // dp
    public List<Node> minDistance(String word1, String word2) {
        List<Node> res = new LinkedList<>();

        // 以 word1 变成 word2 为求解方向
        int m = word1.length(), n = word2.length();
        Node[][] dp = new Node[m + 1][n + 1];

        // base case
        dp[0][0] = new Node(0, 0);
        for (int i = 1; i <= m; i++) {
            // 需要做删除操作
            dp[i][0] = new Node(i, 2);
        }
        for (int j = 1; j <= n; j++) {
            // 需要做插入操作
            dp[0][j] = new Node(j, 1);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 做 skip 即可
                    dp[i][j] = new Node(dp[i - 1][j - 1].val, 0);
                } else {
                    Node choice = makeChoice(dp[i - 1][j - 1].val, dp[i][j - 1].val, dp[i - 1][j].val);
                    dp[i][j] = new Node(choice.val + 1, choice.choice);
                }
            }
        }

        // dp[m][n]
        int i = m, j = n;
        while (true) {
            // 输出选择
            System.out.printf("(%d, %d) 最小编辑距离 %d, %d \n", i, j, dp[i][j].val, dp[i][j].choice);
            res.add(0, dp[i][j]);
            if (i == 0 && j == 0) {
                break;
            }

            if (dp[i][j].choice == 0) {
                i--;
                j--;
            } else if (dp[i][j].choice == 1) {
                j--;
            } else if (dp[i][j].choice == 2) {
                i--;
            } else {
                i--;
                j--;
            }
        }

        return res;
    }

    private Node makeChoice(int leftUp, int left, int up) {
        // 替换
        if (leftUp < left && leftUp < up) {
            return new Node(leftUp, 3);
        }

        // left 最小：插入
        if (left < leftUp && left < up) {
            return new Node(left, 1);
        }

        // up 最小：删除
        if (up < left && up < leftUp) {
            return new Node(up, 2);
        }

        return new Node(leftUp, 3);
    }
}

class Node {
    String[] op = new String[] {
        "跳过",
        "插入",
        "删除",
        "替换"
    };
    int val;

    /**
     * 0 -> skip
     * 1 -> insert
     * 2 -> delete
     * 3 -> replace
     */
    int choice;

    Node(int val, int choice) {
        this.val = val;
        this.choice = choice;
    }

    public String toString() {
        return "[编辑距离：" + val + "，操作：" + op[choice] + "]";
    }
}
