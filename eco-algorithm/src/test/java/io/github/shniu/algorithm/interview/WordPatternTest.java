package io.github.shniu.algorithm.interview;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/28 09
 */
public class WordPatternTest {

    @Test
    public void match() {
        WordPattern wordPattern = new WordPattern();

        boolean res = wordPattern.match("北京 北京 南京", "abb");
        assertFalse(res);

        res = wordPattern.match("北京 北京 南京", "aba");
        assertFalse(res);

        res = wordPattern.match("北京 北京 南京", "abc");
        assertFalse(res);

        res = wordPattern.match("北京 杭州 杭州 北京", "abba");
        assertTrue(res);

        res = wordPattern.match("北京 杭州 杭州 北京", "aabb");
        assertFalse(res);

        res = wordPattern.match("北京 杭州 杭州 南京", "abc");
        assertFalse(res);

        res = wordPattern.match("北京 杭州 杭州 杭州 南京", "abbcd");
        assertFalse(res);
    }
}