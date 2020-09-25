package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.*;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;

/**
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@Title("反转链表")
@Content("反转一个单链表。")
@LevelSimple
@TagLinkedList
public class Algorithm00206 {

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：38.7MB,在所有Java提交中击败了53.10%的用户
     */
//    @AlgorithmBody
    public ListNode reverseList2(ListNode head) {
        // 循环法
        ListNode cur = head;
        ListNode pre = null;
        ListNode old = null;
        while (cur != null) {
            old = pre;
            pre = cur;
            cur = cur.next;
            pre.next = old;
        }
        return pre;
    }

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：38.7MB,在所有Java提交中击败了53.10%的用户
     */
    @AlgorithmBody
    public ListNode reverseList(ListNode head) {
        // 递归法
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

}
