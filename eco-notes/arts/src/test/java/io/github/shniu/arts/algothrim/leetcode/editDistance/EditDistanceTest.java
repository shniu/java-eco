package io.github.shniu.arts.algothrim.leetcode.editDistance;

import org.junit.Test;

public class EditDistanceTest {

    @Test
    public void minDistance() {
        EditDistance editDistance = new EditDistance();
        int distance = editDistance.minDistance1("horse", "ros");
        assert distance == 3;
    }
}