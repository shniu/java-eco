package io.github.shniu.arts.array;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RingBufferFlipMarkerImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test_FlipMarker() {
        RingBuffer<Integer> ringBuffer = new RingBufferFlipMarkerImpl<>(5);

        ringBuffer.put(10);
        ringBuffer.put(30);
        ringBuffer.put(2);
        Integer ele = ringBuffer.take();
        Assertions.assertThat(ele).isEqualTo(10);

        ringBuffer.put(32);
        ringBuffer.put(33);
        ringBuffer.put(34);
        boolean succeed = ringBuffer.put(300);
        Assertions.assertThat(succeed).isFalse();

        ringBuffer.take();
        ringBuffer.take();
        ringBuffer.take();
        ringBuffer.take();
        ringBuffer.take();
        Integer ele2 = ringBuffer.take();
        Assertions.assertThat(ele2).isNull();
    }
}