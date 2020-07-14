package io.github.shniu.algorithm.offer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/7/14 12
 */
public class ReplaceSpaceTest {

    @Test
    public void test_replaceSpace() {
        ReplaceSpace replaceSpace = new ReplaceSpace();

        String s = replaceSpace.replaceSpace("we are happy");
        System.out.println(s);
    }
}