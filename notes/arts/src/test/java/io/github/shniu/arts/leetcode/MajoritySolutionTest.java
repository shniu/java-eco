package io.github.shniu.arts.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MajoritySolutionTest {
    private MajoritySolution majoritySolution;

    @Before
    public void setUp() throws Exception {
        majoritySolution = new MajoritySolution();
    }

    @Test
    public void testMajorityElement() {
        int[] nums = {1, 4, 6, 1, 2, 1, 1, 1, 1};
        int ele = majoritySolution.majorityElement(nums);
        Assert.assertEquals(1, ele);
    }

//    @Test
//    public void testNoMajorityElement() {
//        int[] nums = {1, 4, 6, 1, 2, 1, 1, 1, 1};
//        int ele = majoritySolution.majorityElement(nums);
//        Assert.assertEquals(1, ele);
//    }
}