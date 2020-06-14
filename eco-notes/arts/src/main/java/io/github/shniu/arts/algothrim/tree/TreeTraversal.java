package io.github.shniu.arts.algothrim.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 树的遍历算法
 * <p>
 * 树的遍历分为深度优先遍历和广度优先遍历
 * 深度优先：前序遍历、中序遍历、后序遍历，针对的是根节点
 * 广度优先：层次遍历
 *
 * @author shniu
 * @date 2019/08/16 11:33:35
 */
public class TreeTraversal {

    // 前序遍历：根节点 -> 左子树 -> 右子树
    // https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
    public List<Integer> preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode node, List<Integer> res) {
        if (node != null) {
            res.add(node.val);          // 根
            preorder(node.left, res);   // 左
            preorder(node.right, res);  // 右
        }
    }

    // 使用 stack 模拟递归实现
    public List<Integer> preorderLoop(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;  // 当前指针

        while (curr != null || !stack.isEmpty()) {
            if (curr == null) {  // 左子树可能为 null，就回退到右子树
                curr = stack.removeFirst();
            }
            res.add(curr.val);
            if (curr.right != null) {  // 右子树不为 null，放入 stack 暂存
                stack.addFirst(curr.right);
            }
            curr = curr.left;   // 移动到左子树
        }
        return res;
    }

    // 中序遍历：左子树 -> 根节点 -> 右子树
    // https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
    public List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorder(root.left, res);   // 左
            res.add(root.val);         // 根
            inorder(root.right, res);  // 右
        }
    }

    public List<Integer> inorderLoop(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;  // 当前指针

        while (curr != null || !stack.isEmpty()) {
            // 找到左子树的最左节点
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }

            // 取出当前要访问的节点
            curr = stack.removeFirst();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    // 后序遍历：左子树 -> 右子树 -> 根节点
    // https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
    public List<Integer> postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorder(root.left, res);   // 左
            inorder(root.right, res);  // 右
            res.add(root.val);         // 根
        }
    }

    public List<Integer> postorderLoop(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        TreeNode pre = null;    // 记录上一个访问的节点，跟踪访问右子树后返回根节点的条件判断

        while (curr != null || !stack.isEmpty()) {
            // 找到左子树的最左节点
            while (curr != null) {
                stack.addFirst(curr);
                curr = curr.left;
            }

            curr = stack.peekFirst();
            // 检测当前节点的右子树是否为空或者已经访问过，如果是就记录结果
            if (curr.right == null || curr.right == pre) {
                stack.removeFirst();
                res.add(curr.val);
                pre = curr;
                curr = null;
            } else {
                // 指针指向右子树，重复上面的流程
                curr = curr.right;
            }
        }
        return res;
    }

    // 层次遍历
    // https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, res, 0);
        return res;
    }

    private void levelOrder(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;

        // process current logic
        if (res.size() < level + 1) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);

        // drill down
        levelOrder(root.left, res, level + 1);
        levelOrder(root.right, res, level + 1);
    }

    public List<List<Integer>> levelOrderLoop(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (root != null && !queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                level.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    // 层次遍历
    // https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
    // 107. 二叉树的层次遍历 II
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderBottom(root, res, 0);
        return res;
    }

    private void levelOrderBottom(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;

        int n = res.size();
        if (n < level + 1) {
            res.add(0, new ArrayList<>());
        }
        res.get(n - level - 1).add(root.val);

        levelOrderBottom(root.left, res, level + 1);
        levelOrderBottom(root.right, res, level + 1);
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (root != null && !queue.isEmpty()) {
            res.add(0, new ArrayList<>());
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                res.get(0).add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return res;
    }

    // N 叉树前序遍历
    // https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
    public List<Integer> preorder1(NthTreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(NthTreeNode root, List<Integer> res) {
        if (root != null) {
            res.add(root.val);
            if (root.hasChildren()) {
                for (NthTreeNode node : root.getChildren()) {
                    preorder(node, res);
                }
            }
        }
    }

    public List<Integer> preorder2(NthTreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<NthTreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        NthTreeNode curr;
        while (root != null && !stack.isEmpty()) {
            curr = stack.removeFirst();
            res.add(curr.val);
            if (curr.hasChildren()) {
                for (int i = curr.getChildren().size(); i >= 0; i--) {
                    stack.addFirst(curr.getChildren().get(i));
                }
            }
        }
        return res;
    }
}
