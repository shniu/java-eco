package io.github.shniu.arts.algothrim.stack;

import org.junit.Test;

public class ArrayStackTest {

    @Test
    public void testStack() {
        Stack stack = new ArrayStack(5);
        stack.push("111");
        stack.push("222");
        assert stack.size() == 2;
        assert stack.pop().equals("222");

        stack.push("");
        stack.push("");
        stack.push("");
        stack.push("");
        stack.push("");
//        assert !res;

        stack.pop();
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