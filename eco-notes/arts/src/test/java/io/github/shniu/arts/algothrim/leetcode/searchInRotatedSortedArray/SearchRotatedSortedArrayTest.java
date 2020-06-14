package io.github.shniu.arts.algothrim.leetcode.searchInRotatedSortedArray;

import org.junit.Test;

public class SearchRotatedSortedArrayTest {

    @Test
    public void search() {
        SearchRotatedSortedArray searchRotatedSortedArray = new SearchRotatedSortedArray();
        int i = searchRotatedSortedArray.search(new int[]{
                4, 5, 6, 7, 0, 1, 2
        }, 8);
        assert  i == -1;

        i = searchRotatedSortedArray.search(new int[]{
                4, 5, 6, 7, 0, 1, 2
        }, 0);
        assert i == 4;
    }
}