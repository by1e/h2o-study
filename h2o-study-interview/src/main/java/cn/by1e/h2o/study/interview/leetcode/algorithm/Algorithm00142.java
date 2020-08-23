package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDoublePointer;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 标题：环形链表 II
 * <p>
 * 描述：给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
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
public class Algorithm00142 {

    /**
     * 执行用时：5ms,在所有Java提交中击败了20.73%的用户
     * 内存消耗：40.9MB,在所有Java提交中击败了10.88%的用户
     */
    public ListNode detectCycle(ListNode head) {
        //hashset做法
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return node;
            }
            set.add(node);
            node = node.next;
        }
        return null;
    }

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：40.2MB,在所有Java提交中击败了25.19%的用户
     */
    @AlgorithmBody
    public ListNode detectCycle2(ListNode head) {
        //双指针
        ListNode n1 = head;
        ListNode n2 = head;
        while (n1 != null && n2 != null) {
            n1 = n1.next;
            n2 = n2.next;
            if (n2 != null) {
                n2 = n2.next;
            }
            if (n2 != null && n1 == n2) {
                // 有重叠
                break;
            }
        }
        if (n1 == null || n2 == null) {
            return null;
        }
        ListNode n3 = head;
        while (n2 != n3) {
            // 共同推进到重叠，即为结果
            n2 = n2.next;
            n3 = n3.next;
        }
        return n3;
    }

}
