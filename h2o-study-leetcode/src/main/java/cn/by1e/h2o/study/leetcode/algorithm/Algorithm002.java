package cn.by1e.h2o.study.leetcode.algorithm;

import cn.by1e.h2o.study.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmExecutor;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmInput;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmOutput;
import lombok.Getter;

/**
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
public class Algorithm002 implements Algorithm<Algorithm002.ListNode> {

    @Getter
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode lc = new ListNode(0);
        ListNode lp = lc;
        ListNode lh = lc;
        while (l1 != null || l2 != null) {
            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + lc.val;
            int ovr = val / 10;
            int cur = val % 10;

            lp = lc;
            lc.val = cur;
            lc.next = new ListNode(ovr);
            lc = lc.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (lp.next.val == 0) {
            lp.next = null;
        }
        return lh;
    }

    @Override
    public AlgorithmOutput<ListNode> execute(AlgorithmInput input) {
        return AlgorithmExecutor.function(() -> {
            Object[] params = input.getParams();

            ListNode l1 = (ListNode) params[0];
            ListNode l2 = (ListNode) params[1];

            return addTwoNumbers(l1, l2);
        });
    }

}
