package io.github.shniu.algorithm.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/10/27 07
 */
public class RecursiveBinarySearchTest {

    @Test
    public void test_binarySearch() {
        BinarySearch binarySearch = new RecursiveBinarySearch();

        int[] arr = new int[]{0, 3, 5, 20, 34, 100};

        int pos = binarySearch.search(arr, 10);
        assertEquals(-1, pos);

        pos = binarySearch.search(arr, 3);
        assertEquals(1, pos);

        pos = binarySearch.search(arr, 100);
        assertEquals(5, pos);

        arr = new int[]{};

        pos = binarySearch.search(arr, 100);
        assertEquals(-1, pos);

        arr = new int[]{10};

        pos = binarySearch.search(arr, 100);
        assertEquals(-1, pos);

        pos = binarySearch.search(arr, 10);
        assertEquals(0, pos);

        pos = binarySearch.search(arr, 1);
        assertEquals(-1, pos);
    }
}