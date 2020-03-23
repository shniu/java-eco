package io.github.shniu.arts.algothrim.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommonAncestor {

    // 查找root到任意节点的路径
    public void findPath(TreeNode root, TreeNode p, List<TreeNode> path, List<List<TreeNode>> res) {
        if (root == null) return;

        path.add(root);

        if (root.val == p.val) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (root.left != null) {
            findPath(root.left, p, path, res);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            findPath(root.right, p, path, res);
            path.remove(path.size() - 1);
        }
    }

    public List<TreeNode> findPath2(TreeNode root, TreeNode p, List<TreeNode> path) {
        if (root == null) return null;

        path.add(root);

        if (root.val == p.val) {
            return new ArrayList<>(path);
        }

        if (root.left != null) {
            List<TreeNode> left = findPath2(root.left, p, path);
            path.remove(path.size() - 1);
            if (left != null) return left;
        }

        if (root.right != null) {
            List<TreeNode> right = findPath2(root.right, p, path);
            path.remove(path.size() - 1);
            return right;
        }

        return null;
    }

    // 获取二叉树的最近公共祖先
    // 解法1
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pp = new LinkedList<>();
        getNodePath(root, p, pp);

        LinkedList<TreeNode> qp = new LinkedList<>();
        getNodePath(root, p, qp);

        return getLastCommon(pp, qp);
    }

    // 求两个路径的公共路径
    private TreeNode getLastCommon(LinkedList<TreeNode> pp, LinkedList<TreeNode> qp) {
        int size = pp.size() > qp.size() ? qp.size() : pp.size();

        TreeNode last = null;
        for (int i = 0; i < size; i++) {
            if (pp.get(i) != qp.get(i)) break;
            last = pp.get(i);
        }
        return last;
    }

    // 获取从跟节点到指定节点的路径
    public boolean getNodePath(TreeNode root, TreeNode n, List<TreeNode> path) {
        if (root == null) return false;

        // 加入节点到路径中
        path.add(root);
        // 找到目标节点，直接返回
        if (root == n) return true;

        boolean found;

        // 在左子树中找
        found = getNodePath(root.left, n, path);
        // 找到了就返回
        if (found) return true;

        // 左子树没找到，去右子树找
        found = getNodePath(root.right, n, path);

        // 都没找到，需要回退到上一层，把加入的节点移除掉
        if (!found)
            path.remove(path.size() - 1);

        return found;
    }

    // 解法2 递归
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // terminator
        if (root == null) return null;
        if (root == p) return p;
        if (root == q) return q;

        // drill down
        // 找左子树
        TreeNode left = lowestCommonAncestor2(root.left, p, q);

        // 找右子树
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        // current logic
        // 分几种情况：
        //  p,q 在root的左边
        //  p,q 在root的右边
        //  p和q在root的左边和右边(有可能p和q中的一个充当root)  -> root 就是要求的最近公共祖先
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }
}
