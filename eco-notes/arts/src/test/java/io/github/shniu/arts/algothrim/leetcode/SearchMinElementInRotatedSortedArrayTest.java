package io.github.shniu.arts.algothrim.leetcode;

import org.junit.Test;

public class SearchMinElementInRotatedSortedArrayTest {

    @Test
    public void findMinElementIndex() {
        int minElementIndex = -1;
        SearchMinElementInRotatedSortedArray searchMinElementInRotatedSortedArray = new SearchMinElementInRotatedSortedArray();
        minElementIndex = searchMinElementInRotatedSortedArray.findMinElementIndex(new int[]{10, 11, 12, 14, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(minElementIndex);
        assert minElementIndex == 4;

        minElementIndex = searchMinElementInRotatedSortedArray.findMinElementIndex(new int[]{10, 9});
        System.out.println(minElementIndex);
        assert minElementIndex == 1;

        minElementIndex = searchMinElementInRotatedSortedArray.findMinElementIndex(new int[]{12, 13, 1, 2});
        System.out.println(minElementIndex);
        assert minElementIndex == 2;

        minElementIndex = searchMinElementInRotatedSortedArray.findMinElementIndex(new int[]{12});
        System.out.println(minElementIndex);
        assert minElementIndex == 0;

        minElementIndex = searchMinElementInRotatedSortedArray.findMinElementIndex(new int[]{12,13,14});
        System.out.println(minElementIndex);
        assert minElementIndex == 0;
    }
}