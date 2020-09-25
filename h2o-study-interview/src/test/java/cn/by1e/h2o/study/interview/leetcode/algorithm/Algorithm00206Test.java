package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNodeUtils;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmTestHelper;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.TestAlgorithm;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
@TestAlgorithm(Algorithm00206.class)
public class Algorithm00206Test {

    @Test
    public void test() {
        ListNode head = ListNodeUtils.genListNode("[1,2,3,4,5]");
        AlgorithmTestHelper.smart(head);
    }

}
