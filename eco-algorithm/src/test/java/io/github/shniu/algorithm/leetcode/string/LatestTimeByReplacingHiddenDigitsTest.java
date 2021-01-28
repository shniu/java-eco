package io.github.shniu.algorithm.leetcode.string;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 1736. 替换隐藏数字得到的最晚时间
 * https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits/
 *
 * @author niushaohan
 * @date 2021/1/28 23
 */
public class LatestTimeByReplacingHiddenDigitsTest {

    @Test
    public void maximumTime() {
        LatestTimeByReplacingHiddenDigits latestTimes = new LatestTimeByReplacingHiddenDigits();

        String maximumTime = latestTimes.maximumTime("??:40");
        assertEquals("23:40", maximumTime);

        maximumTime = latestTimes.maximumTime("0?:40");
        assertEquals("09:40", maximumTime);

        maximumTime = latestTimes.maximumTime("2?:4?");
        assertEquals("23:49", maximumTime);
    }
}