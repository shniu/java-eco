package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void sort() {
        Sortable quick = new QuickSort();
        Integer[] arr = new Integer[] {
                10, 4, 9, 12, 3, 23, 100, 1
        };

        quick.sort(arr);
        quick.show(arr);
        assert quick.isSorted(arr);
    }

}