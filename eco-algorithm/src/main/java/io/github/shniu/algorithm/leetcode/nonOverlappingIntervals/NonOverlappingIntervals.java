package io.github.shniu.algorithm.leetcode.nonOverlappingIntervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author niushaohan
 * @date 2021/4/28 18
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

//        for (int[] item : intervals) {
//            System.out.println(Arrays.toString(item));
//        }

        int[] choose = intervals[0];
        int ans = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= choose[1]) {
                ans++;
                choose = intervals[i];
            }
        }

        return intervals.length - ans;
    }

    public static void main(String[] args) {
        NonOverlappingIntervals intervals = new NonOverlappingIntervals();
        int eraseOverlapIntervals = intervals.eraseOverlapIntervals(new int[][]{
                {6, 8}, {2, 4}, {3, 5}, {1, 5}, {5, 9}, {8, 10}
        });
        System.out.println(eraseOverlapIntervals);
    }
}
