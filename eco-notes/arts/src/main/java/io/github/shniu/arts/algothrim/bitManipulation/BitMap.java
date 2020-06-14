package io.github.shniu.arts.algothrim.bitManipulation;

/**
 * 位图
 * Java 中没有bit类型，需要自己实现位图的功能
 */
public class BitMap {
    private long[] words;
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        // long 型变量 64 bits
        words = new long[nbits / 64 + 1];
    }

    /**
     * 设置位图中指定位置为 1
     */
    public void set(int pos) {
        if (pos > nbits) return;

        // 求出 word 的位置
        int wordPos = pos / 64;
        // 求出 bit 的偏移位置
        int bitPos = pos % 64;

        // 把 wordPos 位置上的 bitPos 设置为 1
        words[wordPos] |= (1L << bitPos);
    }

    /**
     * 获取位图中指定位置是否为 true
     */
    public boolean get(int pos) {
        if (pos > nbits) return false;

        int wordPos = pos / 64;
        int bitPos = pos % 64;
        return (words[wordPos] & (1L << bitPos)) != 0;
    }
}
