package io.github.shniu.arts.algothrim.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

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
            return;
        }

        Node p = root;
        while (p != null) {
            if (val < p.val) {
                if (p.left == null) {
                    p.left = new Node(val);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(val);
                    return;
                }
                p = p.right;
            }
        }
    }

    // 删除
    public void delete(int val) {

        // 指向树根
        Node p = root;

        // 记录p的父节点
        Node pp = null;

        // 一直找到要删除的节点为止
        while (p != null && p.val != val) {
            pp = p;
            if (val < p.val) p = p.left;
            else p = p.right;
        }
        // 没有，就直接退出
        if (p == null) return;

        // 如果有两个孩子
        if (p.hasTwoChildren()) {
            // 找到右子树的最小节点
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }

            // 将右子树的最小节点放在删除的位置上
            p.val = minP.val;

            // 变成删除一个孩子和没有孩子的情况
            p = minP;
            pp = minPP;
        }

        Node child = null;
        if (p.hasLeftChild()) child = p.left;
        if (p.hasRightChild()) child = p.right;

        if (pp == null) root = child;
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    // 中序遍历: 实现方式有递归方式，循环+栈的方式
    public List<Integer> inorderTraversal() {
        List<Integer> res = new ArrayList<>();
        inorderRecursive(root, res);
        return res;
    }

    // 使用递归方式
    private void inorderRecursive(Node node, List<Integer> res) {
        if (node != null) {
            if (node.left != null) {
                inorderRecursive(node.left, res);
            }

            res.add(node.val);

            if (node.right != null) {
                inorderRecursive(node.right, res);
            }
        }
    }

    // 前序遍历
    public List<Integer> preorderTraversal() {
        List<Integer> res = new ArrayList<>();
        preorderRecursive(root, res);
        return res;
    }

    private void preorderRecursive(Node node, List<Integer> res) {
        if (node != null) {
            res.add(node.val);

            if (node.left != null) {
                preorderRecursive(node.left, res);
            }

            if (node.right != null) {
                preorderRecursive(node.right, res);
            }
        }
    }

    // 后序遍历
    public List<Integer> postorderTraversal() {
        List<Integer> res = new ArrayList<>();
        postorderRecursive(root, res);
        return res;
    }

    private void postorderRecursive(Node node, List<Integer> res) {
        if (node != null) {
            if (node.left != null) {
                postorderRecursive(node.left, res);
            }

            if (node.right != null) {
                postorderRecursive(node.right, res);
            }

            res.add(node.val);
        }
    }

    // 层次遍历
    // See: https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
    public List<Integer> levelOrderTraversal() {
        return null;
    }

    // 查找节点的前继节点
    public Node prevNode(int val) {
        return null;
    }

    // 查找节点的后继节点
    public Node nextNode(int val) {
        return null;
    }

    /**
     * 高度，求出一颗给定二叉树的确切高度
     * 确定二叉树高度有两种思路：.
     *   第一种是深度优先思想的递归，分别求左右子树的高度。当前节点的高度就是左右子树中较大的那个+1；
     *   第二种可以采用层次遍历的方式，每一层记录都记录下当前队列的长度，这个是队尾，每一层队头从0开始。
     *   然后每遍历一个元素，队头下标+1。直到队头下标等于队尾下标。这个时候表示当前层遍历完成。
     *   每一层刚开始遍历的时候，树的高度+1。最后队列为空，就能得到树的高度。
     *
     * See: https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     */
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 最小节点
    public Node min() {
        if (root == null) {
            return null;
        }

        Node p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    // 找最大节点
    public Node max() {
        if (root == null) {
            return null;
        }

        Node p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

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

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean hasLeftChild() {
            return left != null && right == null;
        }

        public boolean hasRightChild() {
            return left == null && right != null;
        }
    }
}
