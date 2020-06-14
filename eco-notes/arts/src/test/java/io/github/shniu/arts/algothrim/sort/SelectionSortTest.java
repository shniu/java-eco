package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

public class SelectionSortTest {

    @Test
    public void sort() {
        Sortable selection = new SelectionSort();

        Integer[] arr = new Integer[] {
                10, 4, 9, 12, 3
        };
        selection.sort(arr);
        selection.show(arr);
        assert selection.isSorted(arr);
    }
}