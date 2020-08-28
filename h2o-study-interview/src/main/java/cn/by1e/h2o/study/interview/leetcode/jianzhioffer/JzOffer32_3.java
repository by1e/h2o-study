package cn.by1e.h2o.study.interview.leetcode.jianzhioffer;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagBreadthFirstSearch;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagTree;
import cn.by1e.h2o.study.interview.leetcode.jianzhioffer.domain.TreeNode;
import cn.by1e.h2o.study.interview.leetcode.jianzhioffer.support.JzOfferHelper;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：从上到下打印二叉树 III
 * <p>
 * 描述：请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * @author bangquan.qian
 * @date 2020-08-28 11:47
 */
@TagTree
@TagBreadthFirstSearch
public class JzOffer32_3 {

    /**
     * 执行用时：1ms,在所有Java提交中击败了99.76%的用户
     * 内存消耗：40.2MB,在所有Java提交中击败了10.30%的用户
     */
    public static class Solution {

        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> lst = new ArrayList<>();
            if (root == null) {
                return lst;
            }
            Deque<TreeNode> deque = new LinkedList<>();
            deque.addLast(root);
            while (!deque.isEmpty()) {
                LinkedList<Integer> res = new LinkedList<>();
                for (int idx = deque.size(); idx > 0; idx--) {
                    TreeNode node = deque.pollFirst();
                    if (lst.size() % 2 == 0) {
                        //偶数正序
                        res.addLast(node.val);
                    } else {
                        //奇数倒序
                        res.addFirst(node.val);
                    }
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                }
                if (CollectionUtils.isNotEmpty(res)) {
                    lst.add(res);
                }
            }
            return lst;
        }

    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n7.left = n8;
        n7.right = n9;

        TreeNode root = n1;

        List<List<Integer>> lists = JzOfferHelper.run(Solution.class, root);
        ConsoleUtils.json(lists);
    }

}
