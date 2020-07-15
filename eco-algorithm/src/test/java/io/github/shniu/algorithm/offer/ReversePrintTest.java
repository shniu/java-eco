package io.github.shniu.algorithm.offer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author niushaohan
 * @date 2020/7/15 10
 */
@Slf4j
public class ReversePrintTest {

    @Test
    public void rest_reversePrint_RecursiveImplement() {
        // given
        // 10 -> 5 -> 20 -> 2
        ReversePrint.ListNode head = new ReversePrint.ListNode(10);
        head.next = new ReversePrint.ListNode(5);
        head.next.next = new ReversePrint.ListNode(20);
        head.next.next.next = new ReversePrint.ListNode(2);

        // when
        ReversePrint reversePrint = new ReversePrint();
        int[] print2 = reversePrint.reversePrint2(head);

        log.info("{}", print2);
    }
}