package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

import java.util.Arrays;

public class SortableUtilTest {

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

    @Test
    public void test_quickSort() {
        int[] arr = {10, 7, 3, 9, 4, 5};
        SortUtil.quickSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {10};
        SortUtil.quickSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    @Test
    public void test_mergeSort() {
        int[] arr = {10, 7, 3, 9, 4, 5};
        SortUtil.mergeSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {10};
        SortUtil.mergeSort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}