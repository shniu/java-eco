package io.github.shniu.arts.algothrim.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class KthSmallestTest {

    @Test
    public void findKth() {
        KthSmallest kthSmallest = new KthSmallest();
        int[] arr = new int[] {
                3, 10, 4, 4, 9, 5, 20, 90, 2
        };
        int kth = kthSmallest.findKth(arr, 4);
        assert kth == 4;
    }
}