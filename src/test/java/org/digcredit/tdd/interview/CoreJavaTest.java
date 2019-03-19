package org.digcredit.tdd.interview;

import org.junit.Test;

/**
 * Core java test
 * Created by shniu on 2019/3/17.
 */
public class CoreJavaTest {

    @Test
    public void testString() {
        StringBuilder sb = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
    }

    @Test
    public void testCompactString() {
        String s1 = "1111";
        String s2 = "1111";
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        String s3 = new String("1111");
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));
        s3 = s3.intern();
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s3));

        // ===
        System.out.println("--------------");
        Integer a1 = 1;
        Integer a2 = new Integer(1);
        System.out.println(a1 == a2);  // false
        Integer b1 = 1;
        System.out.println(a1 == b1);  // true

        Integer ba1 = 1000;
        Integer ba2 = 1000;
        System.out.println(ba1 == ba2);  // false

        Integer baa1 = 127;
        Integer baa2 = 127;
        System.out.println(baa1 == baa2);  // true

        Integer baa11 = -128;
        Integer baa22 = -128;
        System.out.println(baa11 == baa22);  // true

        Integer baa12 = 128;
        Integer baa23 = 128;
        System.out.println(baa12 == baa23);  // false
    }
}
