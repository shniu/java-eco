package io.github.shniu.arts.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/min-stack/
 * 155. 最小栈
 * <p>
 * 思路1：可以使用两个栈来解决，一个栈存放原始数据，一个栈用来做辅助，首先借用了标准库，其次有点绕
 * https://leetcode-cn.com/problems/min-stack/solution/shi-yong-fu-zhu-zhan-tong-bu-he-bu-tong-bu-python-/
 * 下面是这种方式的实现
 * 思路2：使用链表来手动实现一个栈
 */
public class MinStackWithTwoStack {
    private Deque<Integer> data = new LinkedList<>();
    private Deque<Integer> helper = new LinkedList<>();

    public MinStackWithTwoStack() {
    }

    public void push(int x) {
        data.addFirst(x);
        if (helper.isEmpty() || helper.peekFirst() >= x) {
            helper.addFirst(x);
        }
    }

    public void pop() {
        if (!data.isEmpty()) {
            int top = data.removeFirst();
            if (top == helper.peekFirst()) {
                helper.removeFirst();
            }
        }
    }

    public int top() {
        if (data.isEmpty()) throw new RuntimeException();

        return data.peekFirst();
    }

    public int getMin() {
        if (helper.isEmpty()) throw new RuntimeException();

        return helper.peekFirst();
    }
}
