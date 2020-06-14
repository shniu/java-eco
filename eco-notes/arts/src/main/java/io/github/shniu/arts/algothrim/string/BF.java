package io.github.shniu.arts.algothrim.string;

/**
 * Brute Force 字符串朴素搜索算法
 */
public class BF {

    public int match(String s, String p) {
        int M = s.length();  // 字符串 s 的长度
        int N = p.length();  // 字符串 p 的长度

        for (int i = 0; i <= M - N; i++) {
            int j = 0;
            for (; j < N; j++) {
                if (s.charAt(i + j) != p.charAt(j)) {
                    break;
                }
            }
            if (j == N) {
                return i;
            }
        }
        return -1;
    }
}
