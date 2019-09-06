package io.github.shniu.arts.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajoritySolution {
    public int majorityElement(int[] nums) {

        return sort(nums);
        // return loopUntil(nums);
        // return boyerMooreVote(nums);
    }

    // hash 法
    private int hash(int[] nums) {

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 0);
            }
            counts.put(num, counts.get(num) + 1);
        }



        return 0;
    }

    // 排序法
    private int sort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 暴力法
    private int loopUntil(int[] nums) {
        int majorityCnt = nums.length / 2;

        for (int num : nums) {
            int cnt = 0;
            for (int ele : nums) {
                if (num == ele) {
                    cnt += 1;
                }
            }

            if (cnt >= majorityCnt) {
                return num;
            }
        }

        return -1;
    }

    // Boyer-Moore 投票算法
    private int boyerMooreVote(int[] nums) {
        int count = 0;
        int candidate = -1;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            count += (candidate == num) ? 1 : -1;
        }

        return candidate;
    }
}
