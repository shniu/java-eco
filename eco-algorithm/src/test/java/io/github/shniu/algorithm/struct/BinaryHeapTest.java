package io.github.shniu.algorithm.struct;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/6/30 10
 */
public class BinaryHeapTest {

    @Test
    public void test_emptyHeap() {
        // given
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(Integer.class, 8);

        // when
        Integer item = binaryHeap.get();

        // then
        assertNull(item);
        assertEquals(0, binaryHeap.size());
    }

    @Test
    public void test_multiElements_thenGetHeapTop() {
        // given
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(Integer.class, 8);
        binaryHeap.add(4);
        binaryHeap.add(400);
        binaryHeap.add(10);
        binaryHeap.add(80);
        binaryHeap.add(100);
        binaryHeap.add(20);
        binaryHeap.add(20);
        binaryHeap.add(30);
        binaryHeap.add(20);
        binaryHeap.print();

        // when
        Integer top = binaryHeap.get();

        // then
        assertEquals(400, top.intValue());
        assertEquals(8, binaryHeap.size());

        binaryHeap.print();
    }
}