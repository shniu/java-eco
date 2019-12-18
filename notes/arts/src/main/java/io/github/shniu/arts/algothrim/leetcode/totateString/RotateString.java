package io.github.shniu.arts.algothrim.leetcode.totateString;

import io.github.shniu.arts.algothrim.string.RK;

/**
 * https://leetcode-cn.com/problems/rotate-string
 * 796. 旋转字符串
 */
public class RotateString {

    // 1. 暴力法
    public boolean rotateString1(String A, String B) {
        if (A.equals(B)) {
            return true;
        }

        int n = A.length();

        for (int i = 0; i < n; i++) {
            String rotated = A.substring(i + 1, n) + A.substring(0, i + 1);
            if (rotated.equals(B)) {
                return true;
            }
        }
        return false;
    }

    // 2. 自己实现 substring 的功能
    public boolean rotateString2(String A, String B) {
        if (A.equals(B)) {
            return true;
        }

        int n = A.length();
        char[] chars = A.toCharArray();
        char[] newChars = new char[n];
        for (int i = 0; i < n; i++) {
            // copy i...n-1 to 0...n-i
            System.arraycopy(chars, i + 1, newChars, 0, n - i - 1);
            // copy 0...i to n-i...n
            System.arraycopy(chars, 0, newChars, n - i - 1, i + 1);

            if (new String(newChars).equals(B)) {
                return true;
            }
        }

        return false;
    }

    // 3. A + A, 巧妙的解法
    public boolean rotateString3(String A, String B) {
        return A.length() == B.length() && (A + A).contains(B);
    }

    // 4. 使用 contains 方法就是做字符串匹配，JDK提供的方法已经很高效了，但是为了练习
    // 我们可以自己实现两种字符串匹配算法，来实现 contains 类似的功能，一个是 RK 算法，一个是 KMP 算法
    // RK 算法 - 字符串匹配，BF 是暴力搜索算法，复杂度是O(m*n)，就不再实现了
    public boolean rotateString4(String A, String B) {
        if (A.equals(B)) return true;
        // 构造主串 A + A
        String s = A + A;
        // B 是模式串
        // 问题转化为在 s 中匹配 B
        RK rkSearch = new RK();
        return A.length() == B.length() && rkSearch.match(s, B) >= 0;
    }

    // 5. KMP - 字符串匹配
    public boolean rotateString5(String A, String B) {
        return false;
    }
}
