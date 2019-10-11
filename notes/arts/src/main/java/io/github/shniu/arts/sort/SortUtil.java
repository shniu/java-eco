package io.github.shniu.arts.sort;

public class SortUtil {

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        if (arr.length <= 1) return;

        for (int i = 0; i < arr.length; i++) {
            // 提前退出 flag
            boolean flag = false;

            for (int j = 0; j < arr.length - i - 1; j++) {
                // swap
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    flag = true;
                }
            }

            // 没有数据交换，就提前退出
            if (!flag) break;
        }
    }

    // 插入排序
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
            arr[j + 1] = tmp;
        }
    }

    // 快速排序
    public static void quickSort(int[] arr) {
        // 如果数组长度是1，或者空数组，就自然有序
        if (arr.length <= 1) return;

        // 快排思想：
        // 取数据分区点，将小于分区点的数放左边，将大于分区点的数放右边，直到剩一个元素，就自然有序了
        // 主要使用分治+递归进行实现
        quickSortInternally(arr, 0, arr.length - 1);
    }

    /**
     * 快排的内部实现
     *
     * @param arr 数组
     * @param s   分区的起始位置
     * @param e   分区的结束位置
     */
    private static void quickSortInternally(int[] arr, int s, int e) {
        // 终止条件
        if (s >= e) {
            return;
        }

        // 获取分区点的索引位置
        int pivotIndex = partition(arr, s, e);

        // 继续分区左边的部分
        quickSortInternally(arr, s, pivotIndex - 1);

        // 然后继续分区右边的部分
        quickSortInternally(arr, pivotIndex + 1, e);
    }

    /**
     * 分区函数，用来将要划分的数据分成两部分
     *
     * @param arr 数组
     * @param s   分区起始位置
     * @param e   分区结束位置
     * @return
     */
    private static int partition(int[] arr, int s, int e) {
        // 找分区点, 这里可以使用一些其他算法，让分区点分区之后的数据更均匀，目前简单的取最后一个数
        int pivot = arr[e];

        // 添加一个跟踪的指针位置，用来实现原地分区
        int i = s;

        for (int j = s; j < e; j++) {
            // 如果小于分区点，就往前换，否则，什么也不做
            if (arr[j] < pivot) {
                // 如果出现跟踪位置和遍历位置一样，就不需要交换，只自增跟踪位置就可以
                if (i == j) {
                    i++;
                } else {
                    // 把较小的值交换到前边去
                    int tmp = arr[i];
                    arr[i++] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        // 最后，把分区点放在他应该在的位置
        arr[e] = arr[i];
        arr[i] = pivot;

        // 返回分区点位置
        return i;
    }

    /**
     * 归并排序
     *
     * @param arr 待排序数组
     */
    public static void mergeSort(int[] arr) {
        // 如果数组长度是1，或者空数组，就自然有序
        if (arr.length <= 1) return;

        // 归并排序思想：
        // 每次将程序均分为两部分，一直往下分，直到只有1个或者2个数据，然后再将两个有序的数组合并为一个
        // 分治+递归+合并
        mergeSortInternally(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序的内部实现
     *
     * @param arr 数组
     * @param s   起始位置
     * @param e   终止位置
     */
    private static void mergeSortInternally(int[] arr, int s, int e) {
        // 终止条件
        if (s >= e) {
            return;
        }

        // 取中间位置
        int m = s + ((e - s) >> 2);

        // 分治递归
        mergeSortInternally(arr, s, m);
        mergeSortInternally(arr, m + 1, e);

        // 合并
        merge(arr, s, m, e);
    }

    /**
     * 将有序数组合并为一个
     *
     * @param arr 数组
     * @param s 合并起始位置
     * @param m 两个数组的分割点
     * @param e 合并终止位置
     */
    private static void merge(int[] arr, int s, int m, int e) {
        // 分配一个 e - s 大小的数组
        int[] tmp = new int[e - s + 1];

        int i = s;
        int j = m + 1;
        int k = 0;

        // 移动两个数组的指针
        while (i <= m && j <= e) {
            // 前边的数组中有较小值，就复制到临时数组中
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        // 看哪个数组还有数据
        int remainStart = i;
        int remainEnd = m;
        if (j <= e) {
            remainStart = j;
            remainEnd = e;
        }

        while (remainStart <= remainEnd) {
            tmp[k++] = arr[remainStart++];
        }

        // 把排好序的数组Copy回原来的数组
        for (int z = 0; z < tmp.length; z++) {
            arr[s + z] = tmp[z];
        }

    }


}
