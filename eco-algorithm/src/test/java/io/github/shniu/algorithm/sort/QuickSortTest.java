package io.github.shniu.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author niushaohan
 * @date 2021/4/29 10
 */
public class QuickSortTest {

    @Test
    public void sort() {
        QuickSort quickSort = new QuickSort();

        int[] nums = new int[]{3, 5, 10, 20, 1, 6, 11, 9};
        quickSort.sort(nums);

        System.out.println(Arrays.toString(nums));

        nums = new int[]{3, 5, 5, 10, 20, 1, 6, 11, 9};
        quickSort.sort(nums);

        System.out.println(Arrays.toString(nums));
    }
}