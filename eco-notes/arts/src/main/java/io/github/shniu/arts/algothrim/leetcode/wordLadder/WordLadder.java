package io.github.shniu.arts.algothrim.leetcode.wordLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/word-ladder
 * 127. 单词接龙
 */
public class WordLadder {

    // 1. BFS
    // 核心点：
    //    a. 首先将wordList转成图的邻接表存储，将单词和单词的通用状态构成一个无向图; 通用态：hot 的通用态有 h*t, ho*, *ot, 以此类推
    //    b. 在图中根据 bfs 查找 endWord，命中 endWord 所在的层次就是转换的长度；否则就无法到达
    //       b1. 如果单词列表转换过来的图都是连通的，说明必然存在一个节点是endWord
    //       b2. 如果单词列表转换过来的图存在不连通的，比如有多个子图，说明有可能两个节点之间是不可达的，因为分别在两个子图中
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 问题转换：单词接龙问题，转成基于图的搜索算法，这一步很重要
        //    使用图的邻接表表示法存储
        Map<String, List<String>> graph = new HashMap<>();

        // 不包含结束的单词，直接over
        if (!toGraph(wordList, endWord, graph)) return 0;

        // 已经构造好了邻接表表示的图，进行广度搜索找从起始节点到终点的最短路径
        return bfs(graph, beginWord, endWord);
    }

    // 单词列表转换成图
    private boolean toGraph(List<String> wordList, String endWord, Map<String, List<String>> graph) {
        boolean containsEndWord = false;
        int wordLength = endWord.length();

        // 时间复杂度 O(m*n), n是字典长度，m是单词的长度
        for (String word : wordList) {
            if (endWord.equals(word)) containsEndWord = true;

            for (int i = 0; i < wordLength; i++) {
                String key = toKey(word, i);
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
                String keyToFind = toKey(curr.word, i);
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

    private String toKey(String word, int pos) {
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

    // ---------------------------- 分割线 ----------------------------

    // 2. 双向 bfs
    // 为啥能想到双向 bfs，就先记住吧

    // 关于双向 bfs:
    //   a. 适用于目标节点已知的情况; 初始结点向目标结点和目标结点向初始结点同时扩展，直至在两个扩展方向上出现同一个结点，搜索结束。
    //   b. 为了避免无谓的“组合爆炸”产生，就可以采取双向广度搜索算法，也就是从开始状态和结束状态同时开始搜索，一个向前搜，一个向后找。
    //   c. 好处：我们不妨假设每次搜索的分支因子是 r，如果最短的路径长为 L 的话（也就是搜了 L 层），那么，用一般的 BFS 算法（不考虑去掉重复状态），
    //            总的搜索状态数是 r^L（^表示乘方运算）; 而如果采取双向 BFS 算法，那么，从前往后搜，我们只需要搜索 L/2 层，
    //            从后往前搜，我们也只要搜 L/2 层，因此，搜索状态数是 2*(r^(L/2))，比普通 BFS 就快了很多了
    // 和方法 1 的分析没有区别，只有在 bfs 算法那里使用了 双向bfs
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 将单词接龙问题转换成对图的bfs问题，这个是关键
        // 使用图的邻接表表示法存储
        Map<String, List<String>> graph = new HashMap<>();

        // 将单词列表转换成图表示 & endWord 不在单词列表中的话，直接over
        if (!toGraph(wordList, endWord, graph)) return 0;

        // 双向 bfs
        return bbfs(graph, beginWord, endWord);
    }

    // bidirectional bread first search
    private int bbfs(Map<String, List<String>> graph, String beginWord, String endWord) {
        Map<String, Integer> beginVisited = new HashMap<>();
        beginVisited.put(beginWord, 1);

        Map<String, Integer> endVisited = new HashMap<>();
        endVisited.put(endWord, 1);

        Queue<Pair> beginQueue = new LinkedList<>();
        beginQueue.offer(new Pair(beginWord, 1));

        Queue<Pair> endQueue = new LinkedList<>();
        endQueue.offer(new Pair(endWord, 1));

        // 控制流，控制双向搜索
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            // from begin
            int l = visitWord(graph, beginQueue, beginVisited, endVisited);
            if (l > -1) {
                return l;
            }

            // then from end
            l = visitWord(graph, endQueue, endVisited, beginVisited);
            if (l > -1) {
                return l;
            }
        }

        return 0;
    }

    private int visitWord(Map<String, List<String>> graph, Queue<Pair> queue, Map<String, Integer> visited,
                          Map<String, Integer> oppVisited) {
        Pair curr = queue.poll();

        for (int i = 0; i < curr.word.length(); i++) {
            String keyToFind = toKey(curr.word, i);

            // 遍历邻接节点
            for (String adjWord : graph.getOrDefault(keyToFind, new ArrayList<>())) {
                // 找到就直接返回
                if (oppVisited.containsKey(adjWord)) return curr.level + oppVisited.get(adjWord);

                // 不是要找的，就加入已访问集合 & 加入到待访问队列
                if (!visited.containsKey(adjWord)) {
                    visited.put(adjWord, curr.level + 1);
                    queue.offer(new Pair(adjWord, curr.level + 1));
                }
            }
        }

        return -1;
    }

    // -------- 更加简洁的写法
    // bbfs
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        // 需要两个set来记录两个方向的单词集合
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        int strLen = beginWord.length();

        int len = 1;
        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 交换方向，每次都从元素最少的方向进行 BFS
            if (beginSet.size() > endSet.size()) {
                Set<String> s = beginSet;
                beginSet = endSet;
                endSet = s;
            }

            Set<String> temp = new HashSet<>();
            // BFS
            for (String word : beginSet) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < strLen; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chars[i];
                        chars[i] = c;
                        String target = String.valueOf(chars);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordSet.contains(target)) {
                            visited.add(target);
                            temp.add(target);
                        }

                        chars[i] = old;
                    }
                }
            }
            beginSet = temp;
            len++;
        }

        return 0;
    }
}
