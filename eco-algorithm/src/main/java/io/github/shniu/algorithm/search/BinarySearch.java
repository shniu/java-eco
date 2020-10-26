package io.github.shniu.algorithm.search;

/**
 * @author niushaohan
 * @date 2020/10/18 13
 */
public class BinarySearch {

    // 实现1: while loop
    public int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        // 这里的中止条件是 low <= high, 因为 high = arr.length - 1
        while (low <= high) {
            // 使用 low + (high - low) / 2, 而不使用 (high + low) / 2, 是因为 high + low 可能造成溢出
            // int mid = low + (high - low) / 2;  // 这种方式是可以的，不如位运算效率高
            int mid = low + ((high - low) >> 1);  // 这种方式是最优的，效率最高

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 实现2: 递归
    public int search2(int[] arr, int target) {
        return bsearch(arr, target, 0, arr.length - 1);
    }

    private int bsearch(int[] arr, int target, int low, int high) {
        if (low > high) return -1;

        int mid = low + ((high - low) >> 1);
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return bsearch(arr, low, mid - 1, target);
        } else {
            return bsearch(arr, mid + 1, high, target);
        }
    }
}
