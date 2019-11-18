package io.github.shniu.arts.algothrim.array;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

public class ArrayUtilTest {

    @Test
    public void test_merge1() {

        int[] arr1 = {10, 15, 30, 50};
        int[] arr2 = {4, 18, 30, 45, 100};

        int[] mergedArray = ArrayUtil.merge(arr1, arr2);
        System.out.println(Arrays.toString(mergedArray));
        Assertions.assertThat(mergedArray).isSorted();
        Assertions.assertThat(mergedArray).isNotNull();
        Assertions.assertThat(mergedArray.length).isEqualTo(9);
        Assertions.assertThat(mergedArray[0]).isEqualTo(4);
    }

    @Test
    public void test_merge2() {

        int[] arr1 = {10, 15, 30, 50};
        int[] arr2 = {400, 1800, 3000, 4500, 10000};

        int[] mergedArray = ArrayUtil.merge(arr1, arr2);
        System.out.println(Arrays.toString(mergedArray));
        Assertions.assertThat(mergedArray).isSorted();
        Assertions.assertThat(mergedArray).isNotNull();
        Assertions.assertThat(mergedArray.length).isEqualTo(9);
        Assertions.assertThat(mergedArray[0]).isEqualTo(10);
        Assertions.assertThat(mergedArray[8]).isEqualTo(10000);
    }

    @Test
    public void test_merge3() {

        int[] arr2 = {10, 15, 30, 50};
        int[] arr1 = {400, 1800, 3000, 4500, 10000};

        int[] mergedArray = ArrayUtil.merge(arr1, arr2);
        System.out.println(Arrays.toString(mergedArray));
        Assertions.assertThat(mergedArray).isSorted();
        Assertions.assertThat(mergedArray).isNotNull();
        Assertions.assertThat(mergedArray.length).isEqualTo(9);
        Assertions.assertThat(mergedArray[0]).isEqualTo(10);
        Assertions.assertThat(mergedArray[8]).isEqualTo(10000);
    }
}