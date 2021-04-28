package io.github.shniu.algorithm.divideconquer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/13 09
 */
public class ArrayOrderlinessTest {

    @Test
    public void count() {
        // given
        int[] array = new int[]{5, 6, 1, 3, 7};
        ArrayOrderliness orderliness = new ArrayOrderliness();

        // when
        int count = orderliness.count(array);

        // then
        assertEquals(4, count);
    }
}