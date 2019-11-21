package io.github.shniu.arts.algothrim.disjointSet;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void testUnionFind() {
        UnionFind unionFind = new UnionFind(10);
        unionFind.union(1, 2);
        unionFind.union(1, 5);
        int ele = unionFind.find(2);
        assert ele == 5;
        ele = unionFind.find(1);
        assert ele == 5;
        ele = unionFind.find(3);
        assert ele == 3;
        System.out.println(unionFind);
    }
}