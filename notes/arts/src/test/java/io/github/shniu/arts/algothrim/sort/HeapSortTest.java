package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeapSortTest {

    @Test
    public void sort() {
        Sortable heap = new HeapSort();
        Integer[] arr = new Integer[] {
                10, 4, 9, 12, 3, 23, 100, 1
        };

        heap.sort(arr);
        heap.show(arr);
        assert heap.isSorted(arr);
    }
}