package org.digcredit.tdd.interview;

import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * Created by shniu on 2019/3/15 0015.
 */

public class JavaDataTypeTest {

    @Test
    public void testBasicDataType() {
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println();

        /*try {
            DataInputStream dis = new DataInputStream(null);
            dis.readUnsignedByte();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        byte b1 = -10;
        int i1 = b1 & 0xFF;
        System.out.println(i1);
        byte b2 = -20;
        int i2 = b2;
        System.out.println(i2);

        int i3 = 1000;
        byte b3 = (byte) i3;
        System.out.println(b3);
    }

    @Test
    @Ignore
    public void testGetBytesUsingPrimitives() {

        int n = 10000;
        getMemOfPrimitiveDouble(n);
        getMemOfObjDouble(n);
    }

    private void getMemOfPrimitiveDouble(int n) {
        System.gc();   // force garbage collection
        long memStart = Runtime.getRuntime().freeMemory();
        double[][] a = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n;  ++j) {
                a[i][j] = Math.random();
            }
        }

        long memEnd = Runtime.getRuntime().freeMemory();

        System.out.println(memStart - memEnd);
    }

    private void getMemOfObjDouble(int n) {
        System.gc();   // force garbage collection
        long memStart = Runtime.getRuntime().freeMemory();
        Double[][] a = new Double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n;  ++j) {
                a[i][j] = Math.random();
            }
        }

        long memEnd = Runtime.getRuntime().freeMemory();

        System.out.println(memStart - memEnd);
    }
}
