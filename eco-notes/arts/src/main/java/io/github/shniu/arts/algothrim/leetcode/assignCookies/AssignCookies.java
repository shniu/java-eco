package io.github.shniu.arts.algothrim.leetcode.assignCookies;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/assign-cookies/description/
 * 455. 分发饼干
 */
public class AssignCookies {

    // 可以使用贪心法解决，尽可能拿饼干满足最小胃口值的孩子
    // 前提是要证明贪心法有效的
    // g 表示孩子的胃口值，s 表示饼干的尺寸
    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null) return 0;
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            // 满足
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }

        return i;
    }
}
