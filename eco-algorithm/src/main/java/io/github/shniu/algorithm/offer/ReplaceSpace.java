package io.github.shniu.algorithm.offer;

/**
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 *
 * @author niushaohan
 * @date 2020/7/14 11
 */
public class ReplaceSpace {

    public String replaceSpace(String s) {
        int length = s.length();

        // 计算替换后的字符串的长度
        int i = 0, zeroCount = 0;
        while (i < length) {
            if (s.charAt(i) == ' ') {
                zeroCount++;
            }
            i++;
        }

        int newLength = length + 2 * zeroCount;
        char[] newArray = new char[newLength];

        for (int k = length - 1; k >= 0; k--) {
            char c = s.charAt(k);
            if (c == ' ') {
                newArray[k + 2 * zeroCount] = '0';
                newArray[k + 2 * zeroCount - 1] = '2';
                newArray[k + 2 * zeroCount - 2] = '%';
                zeroCount--;
            } else {
                newArray[k + 2 * zeroCount] = c;
            }
        }

        return new String(newArray);
    }
}
