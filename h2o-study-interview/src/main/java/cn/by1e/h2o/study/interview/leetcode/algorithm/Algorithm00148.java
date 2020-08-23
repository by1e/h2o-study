package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagLinkedList;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagSort;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;

/**
 * 标题：排序链表
 * <p>
 * 描述：在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 标签：排序、链表
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagSort
@TagLinkedList
public class Algorithm00148 {

    /**
     * 执行用时：4ms,在所有Java提交中击败了58.38%的用户
     * 内存消耗：41.8MB,在所有Java提交中击败了76.28%的用户
     */
    @AlgorithmBody
    public ListNode sortList(ListNode head) {
        //类似归并排序
        ListNode mid = findMidAndCut(head);
        if (mid == null) {
            return head;
        }
        return merge(sortList(head), sortList(mid));
    }

    private ListNode merge(ListNode lst1, ListNode lst2) {
        if (lst2 == null) {
            return lst1;
        }
        //双有序链表合并
        //假头
        ListNode n0 = new ListNode(-1);
        ListNode nn = n0;
        ListNode n1 = lst1;
        ListNode n2 = lst2;
        while (n1 != null || n2 != null) {
            int v1 = 0;
            boolean h1 = false;
            if (n1 != null) {
                v1 = n1.val;
                h1 = true;
            }

            int v2 = 0;
            boolean h2 = false;
            if (n2 != null) {
                v2 = n2.val;
                h2 = true;
            }

            if (h1 && h2) {
                if (v1 > v2) {
                    nn.next = n2;
                    n2 = n2.next;
                } else {
                    nn.next = n1;
                    n1 = n1.next;
                }
            } else if (h1) {
                nn.next = n1;
                n1 = n1.next;
            } else {
                nn.next = n2;
                n2 = n2.next;
            }

            nn = nn.next;
        }
        return n0.next;
    }

    private ListNode findMidAndCut(ListNode node) {
        //快慢双指针
        ListNode n1 = node;
        ListNode n2 = node;
        ListNode n1prev = null;
        while (n2 != null) {
            n1prev = n1;
            n1 = n1.next;
            n2 = n2.next;
            if (n2 != null) {
                n2 = n2.next;
            }
        }
        if (n1prev == null) {
            return null;
        }
        //切割
        n1prev.next = null;
        return n1;
    }

}
