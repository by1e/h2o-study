package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.TreeNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmTestHelper;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
public class Algorithm00104Test {

    @Test
    public void test() {
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        TreeNode n19 = new TreeNode(19);

        TreeNode head = n3;

        n3.left = n9;
        n3.right = n20;

        n20.left = n15;
        n20.right = n7;

        n7.left = n19;

        AlgorithmTestHelper.smart(head);
    }

}
