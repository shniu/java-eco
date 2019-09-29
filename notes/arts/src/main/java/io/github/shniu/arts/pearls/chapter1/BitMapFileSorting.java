package io.github.shniu.arts.pearls.chapter1;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 如何给磁盘文件排序？
 * 编程珠玑第一章
 *
 * @author shniu
 * @date 2019/09/29 10:59:11
 */
public class BitMapFileSorting {

    private String input;
    private String output;

    // Java 中没有bit类型，需要自己实现位图的功能
    // 估算数据量级
    // 1. 初始化位图, bitMap
    private BitMap bitMap = new BitMap(10000000);

    public BitMapFileSorting(final String input, final String output) {
        this.input = input;
        this.output = output;
    }

    /**
     * 排序操作
     */
    public void sort() {
        // 2. 遍历输入文件，标识位置索引
        System.out.println("开始排序：");
        try {
            try (FileInputStream fis = new FileInputStream(new File(input));
                 BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {

                String line;
                while ((line = br.readLine()) != null) {
                    if (StringUtils.isEmpty(line.trim())) {
                        continue;
                    }
                    System.out.println("-> " + line);
                    bitMap.set(Integer.valueOf(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. 输出到文件
        System.out.println("输出排序结果：");
        for (int p = 0; p < 10000000; p++) {
            boolean bit = bitMap.get(p);
            if (bit) {
                System.out.println(p);
            }
        }
    }

    static class BitMap {
        private long[] words;
        private int nbits;

        public BitMap() {

        }

        public BitMap(int nbits) {
            this.nbits = nbits;
            words = new long[nbits / 64 + 1];
        }

        /**
         * 设置位图中指定位置为 1
         */
        public void set(int pos) {
            if (pos > nbits) return;

            // 求出word index
            int wordPos = pos / 64;
            // 求出 bit index
            int bitPos = pos % 64;
            words[wordPos] |= (1L << bitPos);
        }

        /**
         * 获取位图中指定位置是否为true
         */
        public boolean get(int pos) {
            if (pos > nbits) return false;

            // 求 word index
            int wordPos = pos / 64;
            int bitPos = pos % 64;
            return (words[wordPos] & (1L << bitPos)) != 0;
        }
    }
}
