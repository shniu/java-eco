package io.github.shniu.arts.sourcecode;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    @Test
    public void testAtomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger();

        assert atomicInteger.get() == 0;
        assert atomicInteger.incrementAndGet() == 1;

        int newVal = atomicInteger.accumulateAndGet(10, Integer::sum);
        assert newVal == 11;
    }
}
