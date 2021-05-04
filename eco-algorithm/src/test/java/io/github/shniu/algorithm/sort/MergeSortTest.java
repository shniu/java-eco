package io.github.shniu.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/29 10
 */
public class MergeSortTest {

    @Test
    public void sort() {
        MergeSort mergeSort = new MergeSort();
        int[] nums = new int[]{3, 5, 10, 20, 1, 6, 11, 9};
        mergeSort.sort(nums);

        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 5, 5, 10, 20, 1, 6, 11, 9};
        mergeSort.sort(nums);

        System.out.println(Arrays.toString(nums));
    }
}