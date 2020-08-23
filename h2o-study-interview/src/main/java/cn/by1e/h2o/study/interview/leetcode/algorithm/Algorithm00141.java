package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDoublePointer;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;

/**
 * 标题：环形链表
 * <p>
 * 描述：给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 标签：数组，双指针
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagArray
@TagDoublePointer
public class Algorithm00141 {

    /**
     * 1、hashset做法
     * 2、双指针，快慢指针
     */

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：39.7MB,在所有Java提交中击败了83.63%的用户
     */
    @AlgorithmBody
    public boolean hasCycle(ListNode head) {
        //双指针
        ListNode n1 = head;
        ListNode n2 = head;
        while (n1 != null && n2 != null) {
            n1 = n1.next;
            n2 = n2.next;
            if (n2 != null) {
                n2 = n2.next;
            }
            if (n1 != null && n1 == n2) {
                // 如果存在环链，快慢指针总会最终相遇
                return true;
            }
        }
        return false;
    }

}
