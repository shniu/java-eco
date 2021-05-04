package io.github.shniu.algorithm.sort;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author niushaohan
 * @date 2021/4/29 09
 */
public class QuickSort {

    public void sort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;

        // partition
        int p = partition(nums, low, high);

        // left
        quickSort(nums, low, p - 1);

        // right
        quickSort(nums, p + 1, high);
    }

    private int partition(int[] nums, int low, int high) {
        // 一般取最后一位作为分割点
        int pivot = nums[high];

        // 双指针
        int partitionPos = low, i = low;

        while (i < high) {
            if (pivot >= nums[i]) {
                if (partitionPos != i) {
                    int tmp = nums[i];
                    nums[i] = nums[partitionPos];
                    nums[partitionPos] = tmp;
                }
                partitionPos++;
            }

            i++;
        }

        nums[high] = nums[partitionPos];
        nums[partitionPos] = pivot;

        return partitionPos;
    }

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(12);
        queue.add(11);
        queue.size();
    }
}
