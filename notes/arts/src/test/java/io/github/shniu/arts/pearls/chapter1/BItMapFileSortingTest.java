package io.github.shniu.arts.pearls.chapter1;

import org.junit.Test;

public class BItMapFileSortingTest {

    @Test
    public void test_sort() {
        BitMapFileSorting fileSorting = new BitMapFileSorting("src/main/a.txt", "");
        fileSorting.sort();
    }

    @Test
    public void test_bitFunc() {
        long mask = 0xffffffffffffffffL;
        long word = 12;

        word |= (1 << 1);
        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 4);
        System.out.println(12 >> 2);
        System.out.println(word);

        System.out.println("======================");
        BitMapFileSorting.BitMap bitMap = new BitMapFileSorting.BitMap(1000);

        int[] a = {20, 11, 434, 40, 799};
        for (int value : a) {
            bitMap.set(value);
        }

        for (int i = 0; i < 1000; i++) {
            if (bitMap.get(i)) {
                System.out.println(i);
            }
        }
    }

}