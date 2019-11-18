package io.github.shniu.arts.algothrim.leetcode.backtrack;

/**
 * 0-1 背包问题
 * 我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
 * 我们现在期望选择几件物品，装载到背包中。在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 */
public class Knapsack01 {
    private int maxW = Integer.MIN_VALUE;

    public void find(int i, int cw, int[] items, int n, int w) {
        // terminator
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }

        // drill down
        // 不放第i个物品
        find(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) {
            // 放第i个物品
            find(i + 1, cw + items[i], items, n, w);
        }
    }

    /**
     * 不超过背包所能装载重量的前提下，让背包中物品的总重量最大
     *
     * @param items 物品列表
     * @param n     物品个数
     * @param w     背包承载重量
     * @return 装入物品的列表
     */
    public int knapsack01(int[] items, int n, int w) {
        find(0, 0, items, n, w);
        return maxW;
    }

    public static void main(String[] args) {
        Knapsack01 knapsack01 = new Knapsack01();
        int maxW = knapsack01.knapsack01(new int[]{
                10, 13, 20, 21, 25, 30, 32, 40, 44, 45
        }, 10, 100);
        System.out.println(maxW);
    }
}
