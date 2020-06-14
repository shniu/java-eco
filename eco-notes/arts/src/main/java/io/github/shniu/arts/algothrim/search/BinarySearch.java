package io.github.shniu.arts.algothrim.search;

public class BinarySearch {

    public static int search(int[] arr, int target) {
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

    // 递归实现
    public static int search2(int[] arr, int target) {
        return bsearch(arr, 0, arr.length - 1, target);
    }

    private static int bsearch(int[] arr, int low, int high, int target) {
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

    // 查找第一个
    public static int searchFirst(int[] arr, int value) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) return mid;
                else high = mid - 1;
            }
        }

        return -1;
    }

    // 查找最后一个
    public static int searchLast(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != value) return mid;
                else low = mid + 1;
            }
        }

        return -1;
    }

    // 查找第一个大于等于指定元素的位置
    public static int searchGte(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                if (mid == 0 || arr[mid - 1] < value) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    // 查找最后一个小于等于给定元素的位置
    public static int searchLte(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 && arr[mid + 1] > value) return mid;
                else low = mid + 1;
            }
        }

        return -1;
    }

}
