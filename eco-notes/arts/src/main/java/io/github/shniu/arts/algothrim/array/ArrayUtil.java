package io.github.shniu.arts.algothrim.array;

import java.util.Arrays;

/**
 * 数组的常用操作.
 *
 * @author shniu
 * @date 2019/09/26 17:03:58
 */
public class ArrayUtil {

    /**
     * 合并两个有序数组.
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] newArray = new int[arr1.length + arr2.length];

        int ptr1 = 0;
        int ptr2 = 0;
        // int copied = 0;

        // copied < newArray.length
        while (ptr1 < arr1.length && ptr2 < arr2.length) {
            // arr1 存在较小元素
            if (arr1[ptr1] < arr2[ptr2]) //noinspection Duplicates
            {
                int endPos = ptr1;

                if (arr1[arr1.length - 1] <= arr2[ptr2]) {
                    endPos = arr1.length;
                } else {
                    for (int i = ptr1; i < arr1.length; i++) {
                        if (arr1[i] > arr2[ptr2]) {
                            endPos = i;
                            break;
                        }
                    }
                }

                // copied += (endPos - ptr1);
                System.arraycopy(arr1, ptr1, newArray, ptr1 + ptr2, endPos - ptr1);
                ptr1 = endPos;
//                if (ptr1 >= arr1.length) {
//                    break;
//                }
            } else //noinspection Duplicates
            {
                int endPos = ptr2;

                if (arr2[arr2.length - 1] <= arr1[ptr1]) {
                    endPos = arr2.length;
                } else {
                    for (int i = ptr2; i < arr2.length; i++) {
                        if (arr2[i] > arr1[ptr1]) {
                            endPos = i;
                            break;
                        }
                    }
                }

                // copied += (endPos - ptr2);
                System.arraycopy(arr2, ptr2, newArray, ptr1 + ptr2, endPos - ptr2);
                ptr2 = endPos;
//                if (ptr2 >= arr2.length) {
//                    break;
//                }
            }
        }

        if (ptr1 >= arr1.length) {
            // copy arr2 剩余的数据
            System.arraycopy(arr2, ptr2, newArray, ptr1 + ptr2, arr2.length - ptr2);
        }

        if (ptr2 >= arr2.length) {
            // copy arr1 剩余的数据
            System.arraycopy(arr1, ptr1, newArray, ptr1 + ptr2, arr1.length - ptr1);
        }

        return newArray;
    }

    // 合并两个有序数组，优化版，这也是归并排序的很核心的一步
    public static int[] merge2(int[] arr1, int[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int[] res = new int[length1+length2];

        int i = 0, j = 0, k = 0;
        while (i < length1 && j < length2) {
            if (arr1[i] < arr2[j]) {
                res[k++] = arr1[i++];
            } else {
                res[k++] = arr2[j++];
            }
        }

        while (i < length1) res[k++] = arr1[i++];
        while (j < length2) res[k++] = arr2[j++];

        return res;
    }
}
