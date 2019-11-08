package io.github.shniu.arts.leetcode.stack;

/**
 * https://leetcode-cn.com/problems/min-stack/
 * 155. 最小栈
 *
 * 思路1：可以使用两个栈来解决，一个栈存放原始数据，一个栈用来做辅助，首先借用了标准库，其次有点绕
 *       https://leetcode-cn.com/problems/min-stack/solution/shi-yong-fu-zhu-zhan-tong-bu-he-bu-tong-bu-python-/
 * 思路2：使用链表来手动实现一个栈，下面就是思路2的实现
 */
public class MinStack {
    private MinNode top;

    public MinStack() {

    }

    public void push(int x) {
        if (top == null) {
            top = new MinNode(x, x);
        } else {
            top = new MinNode(x, Math.min(x, top.min), top);
        }
    }

    public void pop() {
        top = top.next;
    }

    public int top() {
        return top.val;
    }

    public int getMin() {
        return top.min;
    }
}

class MinNode {
    int val;
    int min;
    MinNode next;

    MinNode(int val, int min) {
        this(val, min, null);
    }

    public MinNode(int val, int min, MinNode next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
