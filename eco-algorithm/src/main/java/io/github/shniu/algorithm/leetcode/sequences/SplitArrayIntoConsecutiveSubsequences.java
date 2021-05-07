package io.github.shniu.algorithm.leetcode.sequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * <p>
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 * @author niushaohan
 * @date 2021/5/6 17
 */
public class SplitArrayIntoConsecutiveSubsequences {

    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> numOccurFreq = new HashMap<>();
        Map<Integer, Integer> nextNeeds = new HashMap<>();

        for (int num : nums) {
            update(numOccurFreq, num, 1);
        }

        for (int num : nums) {
            if (numOccurFreq.getOrDefault(num, 0) == 0) {
                continue;
            }

            if (nextNeeds.getOrDefault(num, 0) > 0) {
                update(numOccurFreq, num, -1);
                update(nextNeeds, num, -1);
                update(nextNeeds, num + 1, 1);
            } else if (numOccurFreq.getOrDefault(num, 0) > 0
                    && numOccurFreq.getOrDefault(num + 1, 0) > 0
                    && numOccurFreq.getOrDefault(num + 2, 0) > 0) {
                update(numOccurFreq, num, -1);
                update(numOccurFreq, num + 1, -1);
                update(numOccurFreq, num + 2, -1);
                update(nextNeeds, num + 3, 1);
            } else {
                return false;
            }
        }

        return true;
    }

    void update(Map<Integer, Integer> map, int key, int incr) {
        int v = map.getOrDefault(key, 0);
        v += incr;
        map.put(key, v);
    }

    public void printSubSequences(int[] nums) {
        Map<Integer, Integer> numOccurFreq = new HashMap<>();
        Map<Integer, List<List<Integer>>> nextNeeds = new HashMap<>();

        for (int num : nums) {
            update(numOccurFreq, num, 1);
        }

        for (int num : nums) {
            if (numOccurFreq.getOrDefault(num, 0) == 0) {
                continue;
            }

            List<List<Integer>> needs = nextNeeds.getOrDefault(num, new ArrayList<>());
            if (needs.size() > 0) {
                update(numOccurFreq, num, -1);

                List<Integer> removedInCurr = needs.remove(0);
                removedInCurr.add(num);

                List<List<Integer>> nextNeedList = nextNeeds.getOrDefault(num + 1, new ArrayList<>());
                nextNeedList.add(removedInCurr);
                nextNeeds.put(num + 1, nextNeedList);
            } else if (numOccurFreq.getOrDefault(num, 0) > 0
                    && numOccurFreq.getOrDefault(num + 1, 0) > 0
                    && numOccurFreq.getOrDefault(num + 2, 0) > 0) {
                update(numOccurFreq, num, -1);
                update(numOccurFreq, num + 1, -1);
                update(numOccurFreq, num + 2, -1);

                List<Integer> newNeeds = new ArrayList<>();
                newNeeds.add(num);
                newNeeds.add(num + 1);
                newNeeds.add(num + 2);

                List<List<Integer>> nextNeedList = nextNeeds.getOrDefault(num + 3, new ArrayList<>());
                nextNeedList.add(newNeeds);
                nextNeeds.put(num + 3, nextNeedList);
            } else {
                return;
            }
        }

        // print
        for (Map.Entry<Integer, List<List<Integer>>> entry : nextNeeds.entrySet()) {
            if (entry.getValue() != null && entry.getValue().size() > 0) {
                for (List<Integer> inner : entry.getValue()) {
                    System.out.println(Arrays.toString(inner.toArray()));
                }
            }
        }
    }
}
