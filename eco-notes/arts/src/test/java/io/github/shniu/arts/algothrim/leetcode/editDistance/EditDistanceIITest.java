package io.github.shniu.arts.algothrim.leetcode.editDistance;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EditDistanceIITest {

    @Test
    public void minDistance() {
        EditDistanceII editDistanceII = new EditDistanceII();
        List<Node> distance = editDistanceII.minDistance("horse", "ros");
        System.out.println(distance);
    }
}