package io.github.shniu.arts.leetcode.largestRectangle;

// https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
// 84. 柱状图中最大的矩形

import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangle {
    // 1. 暴力求解
    public int largestRectangleArea1(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                // 记录最小高度
                minHeight = Math.min(minHeight, heights[j]);
                // 计算面积
                maxArea = Math.max(minHeight * (j - i + 1), maxArea);
            }
        }

        return maxArea;
    }

    // 2. stack
    public int largestRectangleArea2(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.addFirst(-1);

        for (int i = 0; i < heights.length; i++) {
            while (stack.peekFirst() != -1 && heights[stack.peekFirst()] >= heights[i]) {
                // pop stack & calculate the area
                int index = stack.removeFirst();
                maxArea = Math.max(maxArea, heights[index] * (i - stack.peekFirst() - 1));
            }
            stack.addFirst(i);
        }

        while (stack.peekFirst() != -1) {
            maxArea = Math.max(maxArea,
                    heights[stack.removeFirst()] * (heights.length - 1 - stack.peekFirst()));
        }

        return maxArea;
    }
}
