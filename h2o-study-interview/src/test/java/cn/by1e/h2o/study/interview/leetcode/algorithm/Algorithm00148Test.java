package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.ListNodeUtils;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmTestHelper;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
public class Algorithm00148Test {

    @Test
    public void test() {
        ListNode node = ListNodeUtils.genListNode("[3,2,0,4]");
        AlgorithmTestHelper.smart(node);
    }

}
