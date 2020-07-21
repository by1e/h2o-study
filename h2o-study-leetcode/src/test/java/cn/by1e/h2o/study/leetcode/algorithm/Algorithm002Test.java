package cn.by1e.h2o.study.leetcode.algorithm;

import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
public class Algorithm002Test {

    @Test
    public void test() {
        Algorithm002.ListNode na2 = new Algorithm002.ListNode(2);
        Algorithm002.ListNode na3 = new Algorithm002.ListNode(3);
        Algorithm002.ListNode na4 = new Algorithm002.ListNode(4);

        Algorithm002.ListNode nb4 = new Algorithm002.ListNode(4);
        Algorithm002.ListNode nb5 = new Algorithm002.ListNode(5);
        Algorithm002.ListNode nb6 = new Algorithm002.ListNode(6);

        na2.next = na4;
        na4.next = na3;

        nb5.next = nb6;
        nb6.next = nb4;

        Algorithm002.ListNode l1 = na2;
        Algorithm002.ListNode l2 = nb5;

        Object[] params = new Object[]{l1, l2};

        AlgorithmTestHelper.test(Algorithm002.class, params);
    }

}
