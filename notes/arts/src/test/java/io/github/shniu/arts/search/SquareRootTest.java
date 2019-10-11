package io.github.shniu.arts.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquareRootTest {

    @Test
    public void testSqrt() {
        double sqrt = SquareRoot.sqrt(10, 0.000001);
        System.out.println(sqrt);

        int i = Integer.parseInt("210") & 0xFF;
        byte b = (byte) i;

        System.out.println(i);
        System.out.println(b);
    }
}