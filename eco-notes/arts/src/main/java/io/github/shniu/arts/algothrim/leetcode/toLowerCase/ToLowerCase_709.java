package io.github.shniu.arts.algothrim.leetcode.toLowerCase;

/**
 * https://leetcode-cn.com/problems/to-lower-case/
 * 709. 转换成小写字母
 */
public class ToLowerCase_709 {

    public String toLowerCase(String str) {
        char[] newStr = new char[str.length()];
        int delta = 'a' - 'A';
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                newStr[i] = (char) (str.charAt(i) + delta);
            } else {
                newStr[i] = str.charAt(i);
            }
        }

        return new String(newStr);
    }

}
