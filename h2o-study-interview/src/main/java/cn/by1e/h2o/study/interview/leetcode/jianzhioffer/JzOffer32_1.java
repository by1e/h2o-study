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
 * 题目：从上到下打印二叉树
 * <p>
 * 描述：从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 * @author bangquan.qian
 * @date 2020-08-28 11:47
 */
@TagTree
@TagBreadthFirstSearch
public class JzOffer32_1 {

    /**
     * 执行用时：2ms,在所有Java提交中击败了27.01%的用户
     * 内存消耗：39.7MB,在所有Java提交中击败了80.06%的用户
     */
    public static class Solution {

        public int[] levelOrder(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            List<Integer> lst = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                lst.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            int size = lst.size();
            int[] res = new int[size];
            for (int idx = 0; idx < size; idx++) {
                res[idx] = lst.get(idx);
            }
            return res;
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

        int[] arr = JzOfferHelper.run(Solution.class, root);
        ConsoleUtils.json(arr);
    }

}
