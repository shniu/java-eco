package io.github.shniu.arts.algothrim.leetcode.powxn;

import org.junit.Test;

public class PowxnTest {

    @Test
    public void myPow() {
        Powxn powxn = new Powxn();
        double v1 = powxn.myPow1(2.0, 5);
        assert v1 == 32.0;

        double v2 = powxn.myPow1(2.0, -2);
        assert v2 == 0.25;
    }

    @Test
    public void testMyPow2() {
        Powxn powxn = new Powxn();
        double v1 = powxn.myPow2(2.0, 5);
        assert v1 == 32.0;

        double v2 = powxn.myPow2(2.0, -2);
        assert v2 == 0.25;
    }

    @Test
    public void testMyPow3() {
        Powxn powxn = new Powxn();
        double v1 = powxn.myPow3(2.0, 5);
        assert v1 == 32.0;

        double v2 = powxn.myPow3(2.0, -2);
        assert v2 == 0.25;
    }
}