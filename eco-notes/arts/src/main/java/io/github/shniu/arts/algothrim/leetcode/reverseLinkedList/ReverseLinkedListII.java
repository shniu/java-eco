package io.github.shniu.arts.algothrim.leetcode.reverseLinkedList;

/**
 * 92. 反转链表 II
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 */
public class ReverseLinkedListII {

    // 递归？这个理解起来比较复杂

    // 迭代
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prev = null, curr = head;
        // 1. 找到开始反转的位置
        while (m > 1) {
            prev = curr;
            curr = curr.next;
            m--;
            n--;
        }

        // 2. 记录子链表的头和尾
        ListNode subHead = prev, subTail = curr;

        // 3. 反转子链表
        ListNode tmp = null;
        while (n > 0) {
            tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
            n--;
        }

        // 4. 重新链接
        subTail.next = curr;
        if (subHead == null) {
            head = prev;
        } else {
            subHead.next = prev;
        }

        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}
