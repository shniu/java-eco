package io.github.shniu.arts.algothrim.leetcode.climbingStairs;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinCostClimbingStairsTest {

    @Test
    public void minCostClimbingStairs1() {
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        int[] cost = new int[]{
                1, 100, 1, 1, 1, 100, 1, 1, 100, 1
        };
        int minCost = minCostClimbingStairs.minCostClimbingStairs1(cost);
        assert minCost == 6;
    }

    @Test
    public void minCostClimbingStairs2() {
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        int[] cost = new int[]{
                1, 100, 1, 1, 1, 100, 1, 1, 100, 1
        };
        int minCost = minCostClimbingStairs.minCostClimbingStairs2(cost);
        assert minCost == 6;
    }
}