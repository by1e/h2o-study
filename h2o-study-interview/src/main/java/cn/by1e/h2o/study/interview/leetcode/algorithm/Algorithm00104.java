package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagBinaryTree;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDeepFirstSearch;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.TreeNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmSupport;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 标题：二叉树的最大深度
 * <p>
 * 描述：给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 标签：二叉树，深度优先遍历
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagBinaryTree
@TagDeepFirstSearch
public class Algorithm00104 extends AlgorithmSupport {

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：39.8MB,在所有Java提交中击败了47.91%的用户
     */
    //@AlgorithmBody
    public int maxDepth(TreeNode root) {
        //递归法
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 执行用时：1ms,在所有Java提交中击败了16.78%的用户
     * 内存消耗：39.6MB,在所有Java提交中击败了79.95%的用户
     */
    @AlgorithmBody
    public int maxDepth2(TreeNode root) {
        //使用bfs实现
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        int size;
        while ((size = queue.size()) > 0) {
            while (size-- > 0) {
                // size==0意味着该层遍历完毕
                TreeNode pop = queue.poll();
                if (pop.left != null) {
                    queue.offer(pop.left);
                }
                if (pop.right != null) {
                    queue.offer(pop.right);
                }
            }
            level++;
        }
        return level;
    }

}
