package io.github.shniu.arts.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeetCode433Test {

    @Test
    public void minMutation() {
        LeetCode433 leetCode433 = new LeetCode433();
        int cnt = leetCode433.minMutation2("AAAAACCC",
                "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"});
        System.out.println(cnt);
        assert cnt == 3;
    }
}