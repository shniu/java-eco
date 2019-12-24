package io.github.shniu.arts.algothrim.string;

/**
 * Knuth Morris Pratt 字符串匹配算法
 * KMP 算法：
 * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 * https://time.geekbang.org/column/article/71845
 *
 * KMP 算法就是在试图寻找一种规律：
 * 在模式串和主串匹配的过程中，当遇到坏字符后，对于已经比对过的好前缀，能否找到一种规律，将模式串一次性滑动很多位？
 */
public class KMP {

    public int match(String s, String p) {
        int n = s.length(), m = p.length();
        int[] next = getNexts(p);

        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)){
                j = next[j - 1] + 1;
            }

            if (s.charAt(i) == p.charAt(j)) {
                ++j;
            }

            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    private int[] getNexts(String p) {
        return null;
    }
}
