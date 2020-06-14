package org.digcredit.tdd.interview;

import com.carrotsearch.sizeof.RamUsageEstimator;
import org.junit.Test;

/**
 * https://github.com/java-native-access/jna
 *
 * Created by shniu on 2019/3/22.
 */
public class ObjectSizeTest {

    @Test
    public void testObjectSize() {
        System.out.println(RamUsageEstimator.sizeOf(new A()));
        System.out.println(RamUsageEstimator.sizeOf(new int[100]));

        Integer[] integers = new Integer[4];
        integers[0] = 1;
        //integers[1] = 1000;
        System.out.println(RamUsageEstimator.sizeOf(integers));
        System.out.println(RamUsageEstimator.sizeOf(new Integer(2)));
        System.out.println(RamUsageEstimator.sizeOf(2));
        System.out.println(RamUsageEstimator.sizeOf(new String()));
    }

    class A {
        byte b1;
    }

}
