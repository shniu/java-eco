package io.github.shniu.arts.algothrim.bitManipulation;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitMapTest {

    @Test
    public void testBitMap() {
        BitMap bitMap = new BitMap(1000);
        bitMap.set(10);
        boolean b = bitMap.get(10);
        assert b;
    }

}