package io.github.shniu.algorithm.leetcode.stack;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/online-stock-span/
 *
 * @author niushaohan
 * @date 2021/4/30 15
 */
public class OnlineStockSpan {
    private LinkedList<Integer> prices;
    private LinkedList<Integer> counts;

    public OnlineStockSpan() {
        prices = new LinkedList<>();
        counts = new LinkedList<>();
    }

    public int next(int price) {
        // 栈顶元素较小，就合并
        int span = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            span += counts.pop();
        }

        prices.push(price);
        counts.push(span);
        return span;
    }
}
