package io.github.shniu.arts.algothrim.leetcode.majorityElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/majority-element/
 * 169. 求众数
 */
public class MajorityElement {
    // 1. 随机法
    private int majorityElement1(int[] nums) {
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

    // 2. hash 法
    private int majorityElement2(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 0);
            }
            counts.put(num, counts.get(num) + 1);
        }
        /*Arrays.stream(nums).forEach(num -> {
            if (!counts.containsKey(num)) {
                counts.put(num, 0);
            }
            counts.put(num, counts.get(num) + 1);
        });*/
        /*Map<Integer, Long> counts = ((Stream<Integer>) Arrays.stream(nums))
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));*/

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

    // 3. 排序法
    private int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 4. 暴力法
    private int majorityElement4(int[] nums) {
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

    // 5. Boyer-Moore 投票算法
    private int majorityElement5(int[] nums) {
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

    // 6. 分治
    private int majorityElement6(int[] nums) {
        return divide(nums, 0, nums.length - 1);
    }

    private int divide(int[] nums, int low, int high) {
        // terminator
        if (low == high) return nums[low];

        // split big problem
        int mid = low + (high - low) / 2;

        // drill down, sub problems
        int left = divide(nums, low, mid);
        int right = divide(nums, mid + 1, high);

        if (left == right) return left;

        int leftCount = countInRange(nums, left, low, high);
        int rightCount = countInRange(nums, right, low, high);

        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
}
