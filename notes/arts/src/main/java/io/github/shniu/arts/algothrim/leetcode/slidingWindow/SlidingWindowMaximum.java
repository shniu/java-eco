package io.github.shniu.arts.algothrim.leetcode.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * 239. 滑动窗口最大值
 */
public class SlidingWindowMaximum {

    // 1. heap, 遍历元素维护一个大顶堆，在第k次遍历开始，每次输出堆顶元素，然后插入一个新元素进去
    // 使用堆的方式解决可以参考：
    //   https://leetcode.com/problems/sliding-window-maximum/discuss/65936/My-Java-Solution-Using-PriorityQueue

    // 2. deque，双端队列，从一端出数据，一端进数据
    public int[] maxSlidingWindow21(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        if (k == 1) return nums;

        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();

        // 遍历前k个元素，找到最大的元素维持在队列第一个元素
        for (int i = 0; i < k; i++) {
            cleanQueue(nums, queue, i);
        }

        // 每次移动进来一个新的数进来，要维护队列的正确性
        for (int i = k; i < nums.length; i++) {
            res[i - k] = nums[queue.peekFirst()];

            // 首先看第一个元素是不是超过了窗口
            if (i - queue.peekFirst() > k - 1) {
                queue.pollFirst();
            }

            // 继续调整新的数的位置，如果说出现的这个数比较大，就可以把之前的数全清空了
            cleanQueue(nums, queue, i);
        }
        res[nums.length - k] = nums[queue.peekFirst()];

        return res;
    }

    private void cleanQueue(int[] nums, Deque<Integer> queue, int i) {
        while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
            queue.pollLast();
        }
        queue.offerLast(i);
    }

    // 2 简洁一点的写法
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        if (k == 1) return nums;

        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }

            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.offerLast(i);

            if (i >= k - 1) {
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }

        return res;
    }
}
