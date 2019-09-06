package io.github.shniu.arts.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MajoritySolution {
    public int majorityElement(int[] nums) {

        return sort(nums);
        // return loopUntil(nums);
        // return boyerMooreVote(nums);
    }

    // 随机法
    private int randomSelect(int[] nums) {
        Random random = new Random();
        int majorityCnt = nums.length / 2;

        while (true) {
            int candidate = nums[randChoosePos(random, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCnt) {
                return candidate;
            }
        }
    }

    private int countOccurences(int[] nums, int candidate) {
        /*int cnt = 0;
        for (int num : nums) {
            if (candidate == num) {
                cnt += 1;
            }
        }
        return cnt;*/
        return (int) Arrays.stream(nums)
                .filter(num -> candidate == num)
                .count();

    }

    private int randChoosePos(Random random, int min, int max) {
        return random.nextInt(max - min) + min;
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

        /*for (Iterator<Map.Entry<Integer, Integer>> iterator =counts.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<Integer, Integer> entry = iterator.next();

            entry.getKey();
            entry.getValue();
        }*/

        Map.Entry<Integer, Integer> majority = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majority == null || entry.getValue() > majority.getValue()) {
                majority = entry;
            }
        }

        Objects.requireNonNull(majority);
        return majority.getKey();
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
