package io.github.shniu.arts.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Solution394 {

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;

        LinkedList<String> resStack = new LinkedList<>();
        LinkedList<Integer> multiStack = new LinkedList<>();

        for (Character c : s.toCharArray()) {

            // if (c >= '0' && c <= '9') {
            if (Character.isDigit(c)) {
                multi = multi * 10 + Integer.parseInt(c.toString());
            } else if (c == '[') {
                resStack.push(res.toString());
                multiStack.push(multi);
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();

                multi = multiStack.pop();

                for (int i = 0; i < multi; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(resStack.pop() + tmp.toString());
                multi = 0;
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }

    public String decodeStringMoreFast(String s) {

        Deque<Integer> multiStack = new ArrayDeque<>();
        Deque<String> resStack = new ArrayDeque<>();

        int startIndex = 0;
        int length = s.length();

        String res = "";

        while (startIndex < length) {
            char c = s.charAt(startIndex);

            if (Character.isDigit(c)) {
                int multi = c - '0';
                startIndex++;
                while (Character.isDigit(s.charAt(startIndex))) {
                    multi = multi * 10 + (s.charAt(startIndex) - '0');
                    startIndex++;
                }
                // 将数字入栈
                multiStack.addLast(multi);
            } else if (c == '[') {
                resStack.addLast(res);
                res = "";
                startIndex++;
            } else if (c == ']') {
                //noinspection ConstantConditions
                int multi = multiStack.pollLast();
                StringBuilder tmp = new StringBuilder(resStack.pollLast());

                for (int i = 0; i < multi; i++) {
                    tmp.append(res);
                }

                res = tmp.toString();
                startIndex++;
            } else {
                res += c;
                startIndex++;
            }
        }

        return res;
    }
}
