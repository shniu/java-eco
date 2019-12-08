package io.github.shniu.arts.algothrim.leetcode.toLowerCase;

import org.junit.Test;

import static org.junit.Assert.*;

public class ToLowerCase_709Test {

    @Test
    public void toLowerCase() {
        System.out.println('A' - 'a');
        System.out.println('a' - 'A');

        ToLowerCase_709 toLowerCase = new ToLowerCase_709();
        String res = toLowerCase.toLowerCase("Hello");
        assert res.equals("hello");
    }
}