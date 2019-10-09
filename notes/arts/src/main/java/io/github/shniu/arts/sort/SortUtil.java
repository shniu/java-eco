package io.github.shniu.arts.sort;

import java.util.Arrays;
import java.util.Collections;

public class SortUtil {

    public static void bubbleSort(int[] arr) {
        if (arr.length <= 1) return;

        for (int i = 0; i < arr.length; i++) {
            // 提前退出 flag
            boolean flag = false;

            for (int j = 0; j < arr.length - i - 1; j++) {
                // swap
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                    flag = true;
                }
            }

            // 没有数据交换，就提前退出
            if (!flag) break;
        }
    }

    public static void insertionSort(int[] arr) {
        if (arr.length <= 1) return;

        for (int i = 1; i < arr.length; i++) {
            // arr[i]
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (tmp < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j+1] = tmp;
        }
    }
}
