package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

public class BubbleSortTest {

    @Test
    public void sort() {
        Sortable bubble = new BubbleSort();
        Integer[] arr = new Integer[] {
                10, 4, 9, 12, 3
        };

        bubble.sort(arr);
        bubble.show(arr);
        assert bubble.isSorted(arr);
    }
}