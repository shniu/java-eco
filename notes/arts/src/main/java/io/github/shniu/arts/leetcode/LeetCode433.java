package io.github.shniu.arts.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class LeetCode433 {
    // 2. 双向bfs
    public int minMutation1(String start, String end, String[] bank) {
        // 双向的遍历队列
        Queue<String> beginQueue = new LinkedList<>();
        Queue<String> endQueue = new LinkedList<>();
        beginQueue.offer(start);
        endQueue.offer(end);

        char[] meta = new char[]{'A', 'C', 'G', 'T'};
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;

        Set<String> visited = new HashSet<>();
        visited.add(start);
        int level = 0;

        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            if (beginQueue.size() > endQueue.size()) {
                Queue<String> tmp = beginQueue;
                beginQueue = endQueue;
                endQueue = tmp;
            }

            int size = beginQueue.size();
            while (size-- > 0) {
                String curr = beginQueue.poll();

                char[] currCharArr = Objects.requireNonNull(curr).toCharArray();
                for (int i = 0; i < currCharArr.length; i++) {
                    char origin = currCharArr[i];
                    for (char m : meta) {
                        if (origin != m) {
                            currCharArr[i] = m;
                            String next = new String(currCharArr);
                            // 终止条件
                            if (endQueue.contains(next)) return level + 1;

                            if (!visited.contains(next) && bankSet.contains(next)) {
                                beginQueue.offer(next);
                                visited.add(next);
                            }
                        }

                    }
                    currCharArr[i] = origin;
                }
            }
            level++;
        }

        return -1;
    }

    // 对双向bfs实现的优化
    public int minMutation2(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;

        // 使用set
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(start);
        endSet.add(end);

        int level = 0;
        Set<String> visited = new HashSet<>();
        visited.add(start);

        char[] meta = new char[]{'A', 'C', 'G', 'T'};

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> tempSet = new HashSet<>();
            for (String curr : beginSet) {
                char[] currChars = curr.toCharArray();

                for (int i = 0; i < currChars.length; i++) {
                    char origin = currChars[i];
                    for (char m : meta) {
                        if (origin != m) {
                            currChars[i] = m;
                            String next = new String(currChars);
                            if (endSet.contains(next)) return level + 1;

                            if (!visited.contains(next) && bankSet.contains(next)) {
                                visited.add(next);
                                tempSet.add(next);
                            }
                        }
                    }
                    currChars[i] = origin;
                }
            }
            level++;
            beginSet = tempSet;
        }
        return -1;
    }
}
