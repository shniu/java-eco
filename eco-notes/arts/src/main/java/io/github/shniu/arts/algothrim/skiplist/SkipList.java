package io.github.shniu.arts.algothrim.skiplist;

import io.github.shniu.arts.core.Printer;

/**
 * 跳表的实现
 */
public class SkipList implements Printer {
    private final static int MAX_LEVEL = 16;
    private final static double SKIPLIST_P = 0.5;

    private Node head = new Node();
    private int levelCnt = 1;

    // 插入
    public void insert(int score) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.score = score;
        newNode.maxLevel = level;

        // update，记录每一层的指针，方便对每一层做更新
        Node[] update = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].score < score) {
                p = p.forwards[i];
            }
            // 找到每一层合适的插入位置
            update[i] = p;
        }

        for (int i = 0; i < level; i++) {
            // update[i] 指向 head or 第 i 层的 合适位置 的索引
            // update[i].forwards[i] 相当于 head.forwards[i]
            // 修改指针，让newNode插入update[i]后面
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        // update the skip list node level
        if (levelCnt < level) {
            levelCnt = level;
        }
    }

    // 删除
    public void delete(int score) {
        Node[] update = new Node[levelCnt];
        Node p = head;
        for (int i = levelCnt - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].score < score) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].score == score) {
            for (int i = levelCnt - 1; i >= 0; i--) {
                if (update[i].forwards[i] != null && update[i].forwards[i].score == score) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCnt > 1 && head.forwards[levelCnt - 1] == null) {
            levelCnt--;
        }
    }

    // 查找
    public Node find(int score) {
        Node p = head;
        for (int i = levelCnt - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].score < score) {
                p = p.forwards[i];
            }
        }

        if (p.forwards[0] != null && p.forwards[0].score == score) {
            return p.forwards[0];
        }

        return null;
    }

    // 随机函数
    // 要保证随机性
    //   50% 返回 1
    //   25% 返回 2
    //   12.5% 返回 3
    //   ...
    private int randomLevel() {
        int level = 1;
        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    // 顺序输出所有元素
    @Override
    public void print() {
        Node p = head;
        System.out.println("-----");
        while (p.forwards[0] != null) {
            System.out.println(p.forwards[0]);
            p = p.forwards[0];
        }
        System.out.println("-----");
    }

    class Node {
        private String key;  // 暂不使用

        // 根据 score 进行排序
        private int score;

        private Node[] forwards;
        private int maxLevel;

        public Node() {
            this(-1, MAX_LEVEL, 0);
        }

        public Node(int score, int maxNodeLevel, int maxLevel) {
            this.score = score;
            this.forwards = new Node[maxNodeLevel];
            this.maxLevel = maxLevel;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{ score: ");
            sb.append(score);
            sb.append(", levels: ");
            sb.append(maxLevel);
            sb.append(" }");
            return sb.toString();
        }
    }
}
