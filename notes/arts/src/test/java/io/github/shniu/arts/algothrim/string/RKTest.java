package io.github.shniu.arts.algothrim.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class RKTest {

    @Test
    public void match() {
        RK rk = new RK();
        int pos = rk.match("helloworld", "worl");
        System.out.println(pos);
        assert pos == 5;
    }
}