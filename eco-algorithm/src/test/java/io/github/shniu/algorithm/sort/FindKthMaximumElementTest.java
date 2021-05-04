package io.github.shniu.algorithm.sort;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/29 10
 */
public class FindKthMaximumElementTest {

    @Test
    public void kthMaximum() {
        FindKthMaximumElement findKthMaximumElement = new FindKthMaximumElement();
        int kthMaximum = findKthMaximumElement.kthMaximum(
                new int[]{10, 12, 3, 7, 1, 12, 20, 15, 6},
                4);

        System.out.println(kthMaximum); // 12
    }
}