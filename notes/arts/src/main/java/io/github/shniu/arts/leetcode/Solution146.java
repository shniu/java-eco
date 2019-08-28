package io.github.shniu.arts.leetcode;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #146 https://leetcode-cn.com/problems/lru-cache/
 * LRU 缓存机制
 */
public class Solution146 {
}

// 解题思路：哈希表 + 双向链表
class LRUCache {

    private DLinkedNode head;
    private DLinkedNode tail;

    private int capacity;
    private int size;
    private Map<Integer, DLinkedNode> nodeMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();

        head = new DLinkedNode(-1, -1);
        tail = new DLinkedNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int get(int key) {
        DLinkedNode node = nodeMap.get(key);
        if (node != null) {
            // 移动到链表首部
            moveToHead(node);
        }

        return node == null ? -1 : node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = nodeMap.get(key);
        // key存在?
        if (node == null) {
            node = new DLinkedNode(key, value);
            nodeMap.put(key, node);

            if (isFull()) {
                DLinkedNode pop = deleteFromTail();
                if (pop != null) {
                    nodeMap.remove(pop.key);

                    pop.prev = null;
                    pop.next = null;
                    pop = null;
                }
            } else {
                size++;
            }

            addNode(node);
        } else {
            // 更新节点
            node.value = value;

            // 移动到链表首部
            moveToHead(node);
        }
    }

    private void addNode(DLinkedNode node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private DLinkedNode deleteFromTail() {
        if (tail.prev != head) {
            DLinkedNode pop = tail.prev;
            removeNode(pop);
            return pop;
        }

        return null;
    }

    private boolean isFull() {
        return size >= capacity;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    public String toString() {
        List<String> res = new ArrayList<>();
        DLinkedNode iter = head.next;
        while (iter != tail) {
            res.add(String.valueOf(iter.key));
            iter = iter.next;
        }
        return Joiner.on(",").join(res);
    }

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
