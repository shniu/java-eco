package io.github.shniu.algorithm.leetcode.sequences;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/5/6 17
 */
public class SplitArrayIntoConsecutiveSubsequencesTest {

    @Test
    public void isPossible() {
        SplitArrayIntoConsecutiveSubsequences subsequences = new SplitArrayIntoConsecutiveSubsequences();
        boolean possible = subsequences.isPossible(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(possible);

        subsequences.printSubSequences(new int[]{1, 2, 3, 4, 5, 6});
        subsequences.printSubSequences(new int[]{1, 2, 3, 5, 6, 7});
    }
}