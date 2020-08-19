package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNodeUtils;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmTestHelper;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
public class Algorithm00002Test {

    @Test
    public void test() {
        ListNode l1 = ListNodeUtils.genListNode("[2,4,3]");
        ListNode l2 = ListNodeUtils.genListNode("[5,6,4]");

        Object[] params = new Object[]{l1, l2};

        AlgorithmTestHelper.test(Algorithm00002.class, params);
    }

}
