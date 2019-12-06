package io.github.shniu.arts.algothrim.leetcode.decodeWays;

import org.junit.Test;

public class DecodeWaysTest {

    @Test
    public void numDecodings() {
        DecodeWays decodeWays = new DecodeWays();
        int numDecodings = decodeWays.numDecodings1("12");
        System.out.println(numDecodings);
        assert numDecodings == 2;

        numDecodings = decodeWays.numDecodings1("123");
        System.out.println(numDecodings);
        assert numDecodings == 3;

        numDecodings = decodeWays.numDecodings1("12345");
        System.out.println(numDecodings);
        assert numDecodings == 3;

        numDecodings = decodeWays.numDecodings1("1");
        System.out.println(numDecodings);
        assert numDecodings == 1;

        numDecodings = decodeWays.numDecodings1("1212");
        System.out.println(numDecodings);
        assert numDecodings == 5;

        numDecodings = decodeWays.numDecodings1("0");
        System.out.println(numDecodings);
        assert numDecodings == 0;

        numDecodings = decodeWays.numDecodings1("01");
        System.out.println(numDecodings);
        assert numDecodings == 0;
    }

    @Test
    public void testStr() {
        String s = "12345";
        System.out.println(s.substring(1, 3));
    }
}