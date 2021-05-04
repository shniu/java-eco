package io.github.shniu.algorithm.leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author niushaohan
 * @date 2021/4/29 20
 */
public class OpenTheLock {

    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");

        for (String deadend : deadends) {
            if (deadend.equals("0000")) return -1;
            visited.add(deadend);
        }

        int minStep = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (curr.equals(target)) {
                    return minStep;
                }

                for (int j = 0; j < 4; j++) {
                    String plus = plus(curr, j);
                    if (!visited.contains(plus)) {
                        queue.offer(plus);
                        visited.add(plus);
                    }
                }
                for (int j = 0; j < 4; j++) {
                    String minus = minus(curr, j);
                    if (!visited.contains(minus)) {
                        queue.offer(minus(curr, j));
                        visited.add(minus);
                    }
                }
            }

            minStep++;
        }

        return -1;
    }

    String plus(String s, int index) {
        char[] chars = s.toCharArray();
        if (chars[index] == '9') {
            chars[index] = '0';
        } else {
            chars[index] += 1;
        }
        return new String(chars);
    }

    String minus(String s, int index) {
        char[] chars = s.toCharArray();
        if (chars[index] == '0') {
            chars[index] = '9';
        } else {
            chars[index] -= 1;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        OpenTheLock openTheLock = new OpenTheLock();
        int minStep = openTheLock.openLock(new String[]{}, "1221");
        System.out.println(minStep);
    }
}
