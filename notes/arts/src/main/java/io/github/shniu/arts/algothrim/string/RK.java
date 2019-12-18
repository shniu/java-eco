package io.github.shniu.arts.algothrim.string;

/**
 * Rabin-Karp 字符串搜索算法
 * 是对 BF 的优化：主要是在子串比较时加速比较效率，用的是求字符串的 hash，然后比较 hash 值，
 * 不等的话直接忽略，相等的话再继续比较每个字符。重点是 hash 函数的设计
 */
public class RK {
    private static int BASE = 256;
    private static int MOD = 9999;

    public int match(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();

        int pHash = 0, sHash = 0;

        for (int i = 0; i < pLength; i++) {
            pHash = (BASE * pHash + p.charAt(i)) % MOD;
            sHash = (BASE * sHash + s.charAt(i)) % MOD;
        }

        int highestPow = 1;  // pow(BASE, pLength - 1)
        for (int i = 0; i < pLength - 1; i++) {
            highestPow = (BASE * highestPow) % MOD;
        }

        int j;
        for (int i = 0; i <= sLength - pLength; i++) {
            if (pHash == sHash) {
                // 逐个比较每个字符
                for (j = 0; j < pLength; j++) {
                    if (s.charAt(i + j) != p.charAt(j)) {
                        break;
                    }
                }
                if (j == pLength) {
                    return i;
                }
            }

            // 更新 sHash, 计算下一个长度为 pLength 的子串的 hash
            if (i < sLength - pLength) {
                sHash = (BASE * (sHash - highestPow * s.charAt(i)) + s.charAt(i + pLength)) % MOD;
                if (sHash < 0) sHash += MOD;
            }
        }

        return -1;
    }
}
