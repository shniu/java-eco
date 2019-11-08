package io.github.shniu.arts.leetcode.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedStackTest {

    @Test
    public void testLinkedStack() {
        Stack stack = new LinkedStack(5);
        stack.push("111");
        stack.push("222");
        assert stack.size() == 2;
        assert stack.pop().equals("222");

        stack.push("");
        stack.push("");
        stack.push("");
        stack.push("");

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        String r1 = stack.pop();
        assert r1.equals("111");
        String r2 = stack.pop();
        assert r2 == null;
    }
}