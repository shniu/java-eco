package io.github.shniu.algorithm.divideconquer;

import java.util.Arrays;

/**
 * 二维平面上有 n 个点，如何快速计算出距离最近的两个点。
 *
 * @author niushaohan
 * @date 2021/4/13 13
 */
public class NearestDistance {

    // 方法1 暴力法，每两个点之间计算一下距离，记录距离最小的两个点
    // 方法2 分治
    public Point[] twoNearestPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return null;
        }

        Point[] res = new Point[2];

        Point[] byX = Arrays.copyOf(points, points.length);
        Point[] byY = Arrays.copyOf(points, points.length);

        Arrays.sort(byX, (p1, p2) -> Double.compare(p1.x, p2.x));
        Arrays.sort(byY, (p1, p2) -> Double.compare(p1.y, p2.y));

        System.out.println(Arrays.toString(byX));
        System.out.println(Arrays.toString(byY));

        // twoNearestPoints(points, res, 0, points.length - 1);
        return res;
    }

    void twoNearestPoints(Point[] points, Point[] res, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        twoNearestPoints(points, res, low, mid);
        twoNearestPoints(points, res, mid + 1, high);

        // merge
        merge(points, res, low, mid, high);
    }

    private void merge(Point[] points, Point[] res, int low, int mid, int high) {

    }

    private double distance(Point p1, Point p2) {
        double v = (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
        return Math.sqrt(v);
    }

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + this.x + "," + this.y + ")";
        }
    }
}
