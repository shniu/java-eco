package io.github.shniu.arts.algothrim.leetcode.climbingStairs;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 70. 爬楼梯
 */
public class ClimbingStairs_70 {

    // 1. 递归
    public int climbStairs1(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // 2. 记忆化递归
    public int climbStairs2(int n) {
        int[] memo = new int[n + 1];
        return climbStairs2(n, memo);
    }

    private int climbStairs2(int n, int[] memo) {
        // terminator
        if (n <= 2) return n;

        // process current logic
        if (memo[n] > 0) return memo[n];

        // drill down
        memo[n] = climbStairs2(n - 1, memo) + climbStairs2(n - 1, memo);
        return memo[n];
    }

    // 3. 动态规划
    public int climbStairs3(int n) {
        if (n <= 2) return n;
        int p1 = 1, p2 = 2, total = 0;
        for (int i = 2; i <= n; i++) {
            total = p1 + p2;
            p1 = p2;
            p2 = total;
        }
        return total;
    }
}
