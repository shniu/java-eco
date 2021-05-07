package io.github.shniu.algorithm.leetcode.other;

/**
 * @author niushaohan
 * @date 2021/5/6 16
 */
public class DecodeXoredArray {

    public int[] decode(int[] encoded, int first) {
        int originArrayLen = encoded.length + 1;
        int[] originArr = new int[originArrayLen];

        originArr[0] = first;
        for (int i = 0; i < originArrayLen - 1; i++) {
            originArr[i + 1] = encoded[i] ^ originArr[i];
        }

        return originArr;
    }
}
