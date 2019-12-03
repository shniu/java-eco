package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortTest {

    @Test
    public void sort() {
        Sortable merge = new MergeSort();
        Integer[] arr = new Integer[] {
                10, 4, 9, 12, 3, 23, 100, 1
        };

        merge.sort(arr);
        merge.show(arr);
        assert merge.isSorted(arr);
    }
}