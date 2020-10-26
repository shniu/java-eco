package io.github.shniu.algorithm.search;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/10/18 13
 */
public class WhileLoopBinarySearchTest {

    @Test
    public void test_searchWhileLoop() {
        BinarySearch binarySearch = new WhileLoopBinarySearch();

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