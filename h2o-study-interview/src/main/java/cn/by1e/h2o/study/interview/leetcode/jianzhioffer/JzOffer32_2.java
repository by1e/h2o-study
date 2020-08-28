package cn.by1e.h2o.study.interview.leetcode.jianzhioffer;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagBreadthFirstSearch;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagTree;
import cn.by1e.h2o.study.interview.leetcode.jianzhioffer.domain.TreeNode;
import cn.by1e.h2o.study.interview.leetcode.jianzhioffer.support.JzOfferHelper;
import cn.by1e.ox.core.util.ConsoleUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目：从上到下打印二叉树 II
 * <p>
 * 描述：从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 * @author bangquan.qian
 * @date 2020-08-28 11:47
 */
@TagTree
@TagBreadthFirstSearch
public class JzOffer32_2 {

    public static class Solution {

        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> lst = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> res = new LinkedList<>();
                for (int idx = queue.size(); idx > 0; idx--) {
                    TreeNode node = queue.poll();
                    res.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                lst.add(res);
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
