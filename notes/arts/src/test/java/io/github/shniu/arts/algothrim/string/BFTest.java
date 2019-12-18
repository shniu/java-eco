package io.github.shniu.arts.algothrim.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class BFTest {

    @Test
    public void match() {
        BF bf = new BF();
        int res = bf.match("abcdefghijk", "ijk");
        System.out.println(res);
        assert res == 8;
    }
}