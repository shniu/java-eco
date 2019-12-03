package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

public class InsertionSortTest {

    @Test
    public void sort() {
        Sortable insertion = new InsertionSort();
        Integer[] arr = new Integer[] {
                10, 4, 9, 12, 3
        };

        insertion.sort(arr);
        insertion.show(arr);
        assert insertion.isSorted(arr);
    }
}