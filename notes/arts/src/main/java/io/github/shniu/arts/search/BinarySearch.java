package io.github.shniu.arts.search;

public class BinarySearch {

    public static int search(int[] arr, int value) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            // 使用 low + (high - low) / 2, 而不使用 (high + low) / 2, 是因为 high + low 可能造成溢出
            // int mid = low + (high - low) / 2;
            int mid = low + ((high - low) >> 1);  // 这种方式是最优的，效率最高

            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    // 递归实现
    public static int search2(int[] arr, int value) {
        return bsearch(arr, 0, arr.length - 1, value);
    }

    private static int bsearch(int[] arr, int low, int high, int value) {
        if (low > high) return -1;

        int mid = low + ((high - low) >> 1);
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] > value) {
            return bsearch(arr, low, mid - 1, value);
        } else {
            return bsearch(arr, mid + 1, high, value);
        }
    }
}
