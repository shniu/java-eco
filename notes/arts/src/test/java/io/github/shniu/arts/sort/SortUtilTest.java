package io.github.shniu.arts.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SortUtilTest {

    @Test
    public void test_bubbleSort() {
        int[] arr = {19, 11, 34, 3, 9, 15};
        SortUtil.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test_insertionSort() {
        int[] arr = {19, 11, 34, 3, 9, 15};
        SortUtil.insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}