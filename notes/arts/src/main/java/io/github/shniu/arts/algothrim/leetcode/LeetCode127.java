package io.github.shniu.arts.algothrim.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author shniu
 * @date 2019/11/01 16:15:05
 */
public class LeetCode127 {

    // 1. BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 问题转换：单词接龙问题，转成基于图的搜索算法，这一步很重要
        //    使用图的邻接表表示法存储
        Map<String, List<String>> graph = new HashMap<>();

        // 不包含结束的单词，直接over
        if (!toGraph(wordList, endWord, graph)) return 0;

        // 已经构造好了邻接表表示的图，进行广度搜索找从起始节点到终点的最短路径
        return bfs(graph, beginWord, endWord);
    }

    private boolean toGraph(List<String> wordList, String endWord, Map<String, List<String>> graph) {
        boolean containsEndWord = false;
        int wordLength = endWord.length();

        // 时间复杂度 O(m*n), n是字典长度，m是单词的长度
        for (String word : wordList) {
            if (endWord.equals(word)) containsEndWord = true;

            for (int i = 0; i < wordLength; i++) {
                String key = generateKey(word, i);
                List<String> vertexList = graph.getOrDefault(key, new LinkedList<>());
                vertexList.add(word);
                graph.put(key, vertexList);
            }
        }

        return containsEndWord;
    }

    private int bfs(Map<String, List<String>> graph, String beginWord, String endWord) {
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(beginWord, 1));

        int wordLength = beginWord.length();

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            for (int i = 0; i < wordLength; i++) {
                String keyToFind = generateKey(curr.word, i);
                for (String adjacencyWord : graph.getOrDefault(keyToFind, new ArrayList<>())) {
                    if (adjacencyWord.equals(endWord)) return curr.level + 1;

                    if (!visited.contains(adjacencyWord)) {
                        visited.add(adjacencyWord);
                        queue.offer(new Pair(adjacencyWord, curr.level + 1));
                    }
                }
            }
        }

        return 0;
    }

    private String generateKey1(String word, int pos) {
        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(pos, '*');
        return sb.toString();
    }

    private String generateKey(String word, int pos) {
        char[] chars = word.toCharArray();
        chars[pos] = '*';
        return new String(chars);
    }

    class Pair {
        String word;
        int level;
        public Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
}
