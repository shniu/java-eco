package io.github.shniu.arts.algothrim.queue;

import io.github.shniu.arts.algothrim.stack.LinkedStack;
import io.github.shniu.arts.algothrim.stack.Stack;

/**
 * 使用栈实现队列: 双栈实现队列
 * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/dui-lie-shi-xian-zhan-zhan-shi-xian-dui-lie
 */
public class StackImplQueue {
    private Stack s1, s2;

    public StackImplQueue() {
        s1 = new LinkedStack();
        s2 = new LinkedStack();
    }

    // API
    public void push(String item) {
        s1.push(item);
    }

    public String pop() {
        peak();
        return s2.pop();
    }

    // 时间复杂度 O(n)
    public String peak() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peak();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
