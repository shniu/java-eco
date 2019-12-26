package io.github.shniu.arts.algothrim.leetcode.wordLadder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/word-ladder-ii/
 * 126. 单词接龙 II
 */
public class WordLadderII {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new LinkedList<>();
        if (!wordList.contains(endWord)) return res;
        bfs(beginWord, endWord, wordList, res);
        return res;
    }

    private void bfs(String beginWord, String endWord, List<String> wordList, List<List<String>> res) {
        // why List<String>? 需要记录历史的转换序列
        Queue<List<String>> queue = new LinkedList<>();
        queue.offer(new ArrayList<String>() {{
            add(beginWord);
        }});

        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        boolean isFound = false;  // 提前结束标识

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> currVisited = new HashSet<>();
            // each level
            for (int i = 0; i < size; i++) {
                List<String> curr = queue.poll();
                String last = curr.get(curr.size() - 1);
                List<String> neighbors = getNeighbors(last, wordSet);

                // 把当前单词对应的所有单词都遍历完
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                            curr.add(neighbor);
                            res.add(new ArrayList<>(curr));
                            curr.remove(curr.size() - 1);
                        }

                        curr.add(neighbor);
                        queue.offer(new ArrayList<>(curr));
                        curr.remove(curr.size() - 1);
                        currVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(currVisited);
            if (isFound) break;
        }
    }

    // 找到单词表中经过一次转换可以到达的所有单词，相当于是同一层
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbor = new ArrayList<>();
        char[] wordChars = word.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < wordChars.length; i++) {
                if (wordChars[i] == ch) continue;
                char old = wordChars[i];
                wordChars[i] = ch;
                if (wordSet.contains(String.valueOf(wordChars))) {
                    neighbor.add(String.valueOf(wordChars));
                }
                wordChars[i] = old;
            }
        }
        return neighbor;
    }
}
