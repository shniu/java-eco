package io.github.shniu.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
 * 返回一个包含所有深度的链表的数组。
 *
 * @author niushaohan
 * @date 2021/3/17 17
 */
public class ListOfDepth {
    // 1. 递归
    // 2. 层次遍历
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return null;
        }

        List<ListNode> res = new ArrayList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                assert node != null;

                if (res.size() < level) {
                    res.add(new ListNode(node.val));
                } else {
                    ListNode listNode = res.get(level - 1);
                    while (listNode.next != null) {
                        listNode = listNode.next;
                    }
                    listNode.next = new ListNode(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            level++;
        }

        ListNode[] listNodes = new ListNode[res.size()];
        return res.toArray(listNodes);
    }

    // 递归方法
    public ListNode[] listOfDepth2(TreeNode tree) {
        List<ListNode> listNodes = new ArrayList<>();
        search(tree, 0, listNodes);
        ListNode[] nodesArray = new ListNode[listNodes.size()];
        return listNodes.toArray(nodesArray);
    }

    void search(TreeNode node, int level, List<ListNode> listNodes) {
        // terminator
        if (node == null) {
            return;
        }

        if (listNodes.size() <= level) {
            listNodes.add(new ListNode(node.val));
        } else {
            ListNode listNode = listNodes.get(level);
            while (listNode.next != null) {
                listNode = listNode.next;
            }
            listNode.next = new ListNode(node.val);
        }

        search(node.left, level + 1, listNodes);
        search(node.right, level + 1, listNodes);
    }
}
