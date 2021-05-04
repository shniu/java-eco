package io.github.shniu.algorithm.leetcode.tree;

/**
 * @author niushaohan
 * @date 2021/4/28 22
 */
public class PopulatingNextRightPointersInEachNode {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        connect(root.left, root.right);
        return root;
    }

    private void connect(Node left, Node right) {
        if (left == null || right == null) {
            return;
        }

        left.next = right;

        connect(left.left, left.right);
        connect(right.left, right.left);

        connect(left.right, right.left);
    }
}
