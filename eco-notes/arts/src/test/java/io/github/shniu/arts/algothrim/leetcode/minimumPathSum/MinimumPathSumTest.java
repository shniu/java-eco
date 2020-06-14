package io.github.shniu.arts.algothrim.leetcode.minimumPathSum;

import org.junit.Test;

public class MinimumPathSumTest {

    @Test
    public void minPathSum() {
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int minPathSum = minimumPathSum.minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        assert minPathSum == 7;
    }
}