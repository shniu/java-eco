package io.github.shniu.arts.algothrim.leetcode.climbingStairs;

import org.junit.Test;

public class MinCostClimbingStairs74670Test {

    @Test
    public void minCostClimbingStairs1() {
        MinCostClimbingStairs_746 minCostClimbingStairs746 = new MinCostClimbingStairs_746();
        int[] cost = new int[]{
                1, 100, 1, 1, 1, 100, 1, 1, 100, 1
        };
        int minCost = minCostClimbingStairs746.minCostClimbingStairs1(cost);
        assert minCost == 6;
    }

    @Test
    public void minCostClimbingStairs2() {
        MinCostClimbingStairs_746 minCostClimbingStairs746 = new MinCostClimbingStairs_746();
        int[] cost = new int[]{
                1, 100, 1, 1, 1, 100, 1, 1, 100, 1
        };
        int minCost = minCostClimbingStairs746.minCostClimbingStairs2(cost);
        assert minCost == 6;
    }
}