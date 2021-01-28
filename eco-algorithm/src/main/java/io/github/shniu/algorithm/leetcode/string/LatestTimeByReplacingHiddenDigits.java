package io.github.shniu.algorithm.leetcode.string;

/**
 * @author niushaohan
 * @date 2021/1/28 19
 */
public class LatestTimeByReplacingHiddenDigits {

    public String maximumTime(String time) {
        char[] t = time.toCharArray();

        t[0] = t[0] != '?' ? t[0] : (t[1] == '?' || t[1] <= '3') ? '2' : '1';
        t[1] = t[1] != '?' ? t[1] : t[0] == '2' ? '3' : '9';
        t[3] = t[3] != '?' ? t[3] : '5';
        t[4] = t[4] != '?' ? t[4] : '9';

        return new String(t);
    }

}
