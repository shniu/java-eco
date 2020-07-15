package io.github.shniu.algorithm.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 *
 * @author niushaohan
 * @date 2020/7/15 09
 */
public class ReversePrint {

    public int[] reversePrint2(ListNode head) {
        // terminator
        // process current logic
        // drill down
        // reverse state

        List<Integer> list = new ArrayList<>();
        reverse(head, list);

        int[] print = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            print[i] = list.get(i);
        }
        return print;
    }

    private void reverse(ListNode head, List<Integer> print) {
        // terminator
        if (head == null) {
            return;
        }

        // drill down
        reverse(head.next, print);

        // process current logic
        print.add(head.val);
    }

    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();

        // 打印功能的函数，最好不改变原来链表的结构，使用临时指针 p
        ListNode p = head;
        while (p != null) {
            stack.addFirst(p.val);
            p = p.next;
        }

        int[] print = new int[stack.size()];
        int i = 0;
        while (stack.peekFirst() != null) {
            print[i++] = stack.removeFirst();
        }

        return print;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
