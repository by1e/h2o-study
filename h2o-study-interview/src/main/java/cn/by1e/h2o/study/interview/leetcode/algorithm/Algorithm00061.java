package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDoublePointer;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagLinkedList;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：旋转链表
 * <p>
 * 描述：给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 标签：链表，双指针
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagDoublePointer
@TagLinkedList
public class Algorithm00061 implements Algorithm<Object> {

    /**
     * 执行用时：1ms,在所有Java提交中击败了88.97%的用户
     * 内存消耗：39.8MB,在所有Java提交中击败了5.75%的用户
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        // 尾首相接
        cur.next = head;
        // 继续顺时针遍历找到砍断点
        k = len - k % len;
        while (k > 0) {
            cur = cur.next;
            k--;
        }
        // 截断
        head = cur.next;
        cur.next = null;
        return head;
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        ListNode head = (ListNode) params[0];
        int k = (int) params[1];

        return rotateRight(head, k);
    }

}
