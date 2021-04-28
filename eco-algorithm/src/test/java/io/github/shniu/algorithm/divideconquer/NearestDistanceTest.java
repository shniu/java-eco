package io.github.shniu.algorithm.divideconquer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2021/4/13 15
 */
public class NearestDistanceTest {

    @Test
    public void twoNearestPoints() {
        NearestDistance.Point[] points = {
                new NearestDistance.Point(1, 2),
                new NearestDistance.Point(10, 5),
                new NearestDistance.Point(6, 12),
        };
        NearestDistance nearestDistance = new NearestDistance();
        nearestDistance.twoNearestPoints(points);
    }
}