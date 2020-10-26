package io.github.shniu.algorithm.search;

/**
 * @author niushaohan
 * @date 2020/10/27 07
 */
public class RecursiveBinarySearch implements BinarySearch {

    @Override
    public int search(int[] arr, int target) {
        // 空数组不用再去找了，直接返回
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 如果元素太少，其实没必要使用二分查找
        if (arr.length < 4) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == target) {
                    return i;
                }
            }

            return -1;
        }

        // 元素较多时使用二分查找
        return search(arr, target, 0, arr.length);
    }

    int search(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + ((high - low) >> 1);
        if (arr[mid] > target) {
            return search(arr, target, low, mid - 1);
        } else if (arr[mid] < target) {
            return search(arr, target, mid + 1, high);
        }

        return mid;
    }
}
