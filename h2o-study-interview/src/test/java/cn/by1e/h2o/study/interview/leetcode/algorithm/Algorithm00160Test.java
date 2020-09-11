package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNodeUtils;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmTestHelper;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
public class Algorithm00160Test {

    @Test
    public void test() {
        ListNode headA = ListNodeUtils.genListNode("[4,1]");
        ListNode headB = ListNodeUtils.genListNode("[5,0,1]");
        ListNode headC = ListNodeUtils.genListNode("[8,4,5]");
        headA.next.next = headC;
        headB.next.next.next = headC;
        AlgorithmTestHelper.smart(headA,headB);
    }

}
