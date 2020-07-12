package io.github.shniu.algorithm.offer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/7/13 00
 */
public class FindRepeatNumberTest {
    private FindRepeatNumber findRepeatNumber;

    @Before
    public void setUp() {
        findRepeatNumber = new FindRepeatNumber();
    }

    @Test
    public void test_whenOnlyOneElement() {
        // given
        int[] nums = new int[]{0};

        // when
        int repeatNumber = findRepeatNumber.findRepeatNumber(nums);

        // then
        assertEquals(-1, repeatNumber);
    }

    @Test
    public void test_whenHaveTwoRepeatElements() {
        // given
        int[] nums = new int[]{1, 1};

        // when
        int repeatNumber = findRepeatNumber.findRepeatNumber(nums);

        // then
        assertEquals(1, repeatNumber);
    }

    @Test
    public void test_whenHaveThreeElementsWithRepeatElements() {
        // given
        int[] nums = new int[]{1, 2, 1};

        // when
        int repeatNumber = findRepeatNumber.findRepeatNumber(nums);

        // then
        assertEquals(1, repeatNumber);
    }

    @Test
    public void test_whenHaveThreeElementsWithRepeatCircle() {
        // given
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};

        // when
        int repeatNumber = findRepeatNumber.findRepeatNumber(nums);

        // then
        assertEquals(2, repeatNumber);
    }
}