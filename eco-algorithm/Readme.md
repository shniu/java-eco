
## 分治思想

package: divideconquer

- 求解数组的有序度 io.github.shniu.algorithm.divideconquer.ArrayOrderliness

## 动态规划

### 背包类问题的对比分析

- 0-1 背包问题
对于一组不同重量 weights、不可分割的物品(0 or 1)，我们需要选择一些装入背包，在满足背包最大重量 W 限制的前提下，
背包中物品总重量的最大值是多少呢？

- 0-1 背包问题升级版本

给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。其中第 i 个物品的重量为 wt[i]，价值为 val[i]，
现在让你用这个背包装物品，最多能装的价值是多少？

- 完全背包问题

a. 有一个背包，最大容量为 W，有一系列物品 weights，每个物品的重量为 weights[i]，每个物品的数量无限。请问有多少种方法，能够把背包恰好装满？

b. 有一个背包，最大容量为 W，有一系列物品 weights，每个物品的重量为 weights[i]，每个物品的价值是 value[i], 每个物品的数量无限。
现在让你用这个背包装物品，最多能装的价值是多少？

- 零钱兑换

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
如果没有任何一种硬币组合能组成总金额，返回 -1。你可以认为每种硬币的数量是无限的。(LeetCode 322)

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        // TODO
        return -1;
    }
}
```

- 零钱兑换II

给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 (LeetCode 518)

```java
class Solution {
    public int change(int amount, int[] coins) {
        // TODO
        return -1;
    }
}
```

### 子序列问题对比分析

- 最长回文字串

给你一个字符串 s，找到 s 中最长的回文子串



