package io.github.shniu.algorithm.search;

/**
 * @author niushaohan
 * @date 2020/10/18 13
 */
public class WhileLoopBinarySearch implements BinarySearch {

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
}
