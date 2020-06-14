package io.github.shniu.arts.algothrim.leetcode.lengthOfLastWord;

/**
 * https://leetcode-cn.com/problems/length-of-last-word/
 * 58. 最后一个单词的长度
 */
public class LengthOfLastWord_58 {

    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--;

        if (end < 0) return 0;

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }
}
