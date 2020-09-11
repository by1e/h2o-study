package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagLinkedList;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;

/**
 * 标题：相交链表
 * <p>
 * 描述：编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如下面的两个链表：
 * <p>
 * 在节点 c1 开始相交。
 *
 * <p>
 * 标签：链表
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagLinkedList
public class Algorithm00160 {

    @AlgorithmBody
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //双指针，A走完走B路，B走完走A路
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        boolean fa = true;
        boolean fb = true;
        while (pa != pb) {
            int crs = 0;
            pa = pa.next;
            if (pa == null) {
                pa = fa ? headB : headA;
                fa = !fa;
                crs++;
            }
            pb = pb.next;
            if (pb == null) {
                pb = fb ? headA : headB;
                fb = !fb;
                crs++;
            }
            if (crs == 2) {
                return null;
            }
        }
        //相遇时返回其中一个就行
        return pa;
    }


}
