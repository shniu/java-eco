package io.github.shniu.arts.algothrim.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ThreeSumSolutionTest {
    private ThreeSumSolution threeSumSolution;

    @Before
    public void setUp() {
        threeSumSolution = new ThreeSumSolution();
    }

    @Test
    public void testThreeSum() {
        int[] nums = {0, -1, 1, -1, 2, 1, 1, 3};
        List<List<Integer>> res = threeSumSolution.threeSum(nums);
        // 0 -1 1
        // -1 -1 2
        log.info("{}", res);
        Assert.assertEquals(2, res.size());
    }
}