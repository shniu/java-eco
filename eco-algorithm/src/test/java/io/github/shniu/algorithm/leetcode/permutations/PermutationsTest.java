package io.github.shniu.algorithm.leetcode.permutations;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/2/1 15
 */
public class PermutationsTest {

    @Test
    public void permute() {
        Permutations permutations = new Permutations();
        List<List<Integer>> res = permutations.permute(new int[]{1, 2, 3, 4});

        System.out.println(res);
    }

    @Test
    public void test_permute2() {
        Permutations2 permutations2 = new Permutations2();
        List<List<Integer>> res = permutations2.permute(new int[]{1, 2, 3});
        System.out.println(res);
    }
}