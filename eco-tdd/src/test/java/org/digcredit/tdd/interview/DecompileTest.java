package org.digcredit.tdd.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shniu on 2019/3/19 0019.
 */
public class DecompileTest {

    private static int i1 = 99;
    private static int i2 = 999;

    private String type;
    private float sum;

    public String getType() {
        return type;
    }

    public void test1() {
        String strByBuilder  = new
                StringBuilder().append("aa1").append("bb1").append("cc1").append
                ("dd1").toString();

        String strByConcat = "aa" + "bb" + "cc" + "dd";
        int a = 100;
        int b = 9000;
        Integer aw = 121;
        Integer bw = 3000;

        int c = a + 100;

        type = "bbc";
        sum = 1.0f;

        float d = sum + 1.0f;
    }

    public void testCollection() {
        List l = new ArrayList();
    }
}
