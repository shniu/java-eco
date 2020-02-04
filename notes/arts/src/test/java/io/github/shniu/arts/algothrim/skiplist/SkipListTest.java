package io.github.shniu.arts.algothrim.skiplist;

import org.junit.Test;

public class SkipListTest {

    @Test
    public void testSkipList() {
        SkipList skipList = new SkipList();

        SkipList.Node node = skipList.find(10);
        assert node == null;

        skipList.insert(10);
        skipList.insert(4);
        skipList.insert(16);
        skipList.insert(30);
        skipList.insert(20);
        skipList.insert(24);
        skipList.insert(200);
        skipList.insert(50);
        skipList.insert(9);
        skipList.insert(102);
        skipList.insert(999);

        skipList.print();

        node = skipList.find(30);
        assert node != null;

        node = skipList.find(201);
        assert node == null;

        skipList.delete(23);
        skipList.delete(24);
        skipList.print();
    }
}