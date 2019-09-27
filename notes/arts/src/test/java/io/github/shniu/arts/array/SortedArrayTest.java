package io.github.shniu.arts.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortedArrayTest {

    @Test
    public void test_add() {
        SortedArray sortedArray = new SortedArray(10);
        sortedArray.add(10);
        sortedArray.add(3);
        sortedArray.add(4);
        sortedArray.add(50);
        sortedArray.add(10);
        sortedArray.add(34);
        sortedArray.add(13);
        sortedArray.add(106);
        sortedArray.add(106);
        sortedArray.add(106);
        sortedArray.add(106);
        sortedArray.add(107);
        System.out.println(sortedArray.toString());

        sortedArray.delete(0);
        sortedArray.delete(0);
        System.out.println(sortedArray.toString());
    }

    @Test
    public void test_delete() {
    }
}