package io.github.shniu.arts.leetcode.tree;

/**
 * 实现一个二叉查找树，支持插入、删除、查找
 *
 * @author shniu
 * @date 2019/09/16 18:01:38
 */
public class BinarySearchTree {
    // 树的根节点
    private Node root;

    // 查找节点
    public Node find(int val) {
        Node p = root;
        while (p != null) {
            if (val < p.val) p = p.left;
            else if (val > p.val) p = p.right;
            else return p;
        }
        return null;
    }

    // 插入
    public void insert(int val) {
        if (root == null) {
            root = new Node(val);
        }

        Node p = root;
        while (p != null) {
            if (val > p.val) {
                if (p.right == null) {
                    p.right = new Node(val);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node(val);
                    return;
                }
                p = p.left;
            }
        }
    }

    // 删除
    public Node delete(int val) {
        return null;
    }

    // 中序遍历
    // 前序遍历
    // 后序遍历
    // 层次遍历
    // 查找节点的前继节点
    // 查找节点的后继节点

    // 节点
    public static class Node {
        // 节点值
        private int val;

        // 左孩子
        private Node left;

        // 有孩子
        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}
