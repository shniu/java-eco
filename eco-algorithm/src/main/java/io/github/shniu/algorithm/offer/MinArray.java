package io.github.shniu.algorithm.offer;

import java.util.Objects;

/**
 * @author niushaohan
 * @date 2020/10/18 13
 */
public class MinArray {

    // 思路1：遍历整个数组, 时间复杂度 O(n), 不是最好的解决方法
    public int minArray1(int[] numbers) {
        if (Objects.isNull(numbers)) {
            throw new RuntimeException("numbers must not null");
        }

        int min = Integer.MAX_VALUE;

        for (int number : numbers) {
            min = Math.min(min, number);
        }

        return min;
    }

    // 思路2: 二分查找
    //  旋转数组的切分点的左右都是有序的
    //  3 4 5 1 2
    //  5 1 2 3 4
    public int minArray2(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        int lastElement = numbers[high];

        while (low < high) {
            int mid = low + ((high - low) >> 1);

            if (numbers[mid] < lastElement) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return numbers[low];
    }
}
