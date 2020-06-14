package io.github.shniu.arts.algothrim.leetcode;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class Solution394Test {

    private Solution394 solution394;

    @Before
    public void setUp() {
        solution394 = new Solution394();
    }

    @Test
    public void givenEmpty_shouldReturnEmpty() {
        String res = solution394.decodeString("");
        Assert.assertEquals("", res);
    }

    @Test
    public void givenAParentheses_shouldReturn_aa() {
        String res = solution394.decodeString("2[a]");
        Assert.assertEquals("aa", res);
    }

    @Test
    public void givenMultiParentheses_shouldReturnSuccess() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String res = solution394.decodeString("2[ab]3[c]");
        Assert.assertEquals("ababccc", res);
        stopwatch.stop();
        log.info("time: {}", stopwatch);

        Stopwatch stopwatch2 = Stopwatch.createStarted();
        String res2 = solution394.decodeString("2[ab]3[c]4[niu]10[e]");
        Assert.assertEquals("ababcccniuniuniuniueeeeeeeeee", res2);
        stopwatch2.stop();
        log.info("time: {}", stopwatch2);
    }

    @Test
    public void givenNestedParentheses_shouldReturnOk() {
        String res = solution394.decodeString("2[ab4[d]2[ff]]3[c]");
        Assert.assertEquals("abddddffffabddddffffccc", res);

        String res2 = solution394.decodeString("2[3[4[e]]]");
        Assert.assertEquals("eeeeeeeeeeeeeeeeeeeeeeee", res2);
    }

    @Test
    public void test_moreFast() {
        String res = solution394.decodeStringMoreFast("2[ab4[d]2[ff]]3[c]");
        Assert.assertEquals("abddddffffabddddffffccc", res);
    }
}