package io.github.shniu.algorithm.offer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/7/13 13
 */
public class FindNumberIn2DArrayTest {
    private FindNumberIn2DArray findNumberIn2DArray;

    @Before
    public void setUp() {
        findNumberIn2DArray = new FindNumberIn2DArray();
    }

    @Test
    public void test_givenNullOrEmpty2DArrayThenGetFalse() {
        // given
        int[][] matrix = null;
        int[][] matrix2 = new int[][]{};

        // when
        boolean in2DArray = findNumberIn2DArray.findNumberIn2DArray(matrix, 10);
        boolean in2DArray2 = findNumberIn2DArray.findNumberIn2DArray(matrix2, 10);

        // then
        assertFalse(in2DArray);
        assertFalse(in2DArray2);
    }

    @Test
    public void test_givenTargetInArray_shouldGetTrue() {
        // given
        int[][] matrix = new int[][]{{0, 2, 5}, {1, 3, 7}};
        int target = 7;

        // when
        boolean in2DArray = findNumberIn2DArray.findNumberIn2DArray(matrix, target);

        // then
        assertTrue(in2DArray);
    }

    @Test
    public void test_TargetNotInArray_shouldGetFalse() {
        // given
        int[][] matrix = new int[][]{{0, 1, 2}, {5, 6, 7}, {10, 11, 12}};
        int target = 9;

        // when
        boolean in2DArray = findNumberIn2DArray.findNumberIn2DArray(matrix, target);

        // then
        assertFalse(in2DArray);
    }
}