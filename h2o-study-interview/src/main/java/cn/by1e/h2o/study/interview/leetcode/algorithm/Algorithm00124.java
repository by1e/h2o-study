package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelHard;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDeepFirstSearch;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagTree;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.TreeNode;

/**
 * 标题：二叉树中的最大路径和
 * <p>
 * 描述：给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 标签：树，深度优先遍历
 * <p>
 * 难度：困难
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelHard
@TagTree
@TagDeepFirstSearch
public class Algorithm00124 {

    private int max = Integer.MIN_VALUE;

    /**
     * 执行用时：1ms,在所有Java提交中击败了99.85%的用户
     * 内存消耗：41.9MB,在所有Java提交中击败了12.16%的用户
     */
    @AlgorithmBody
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            //已无子节点，此时返回0
            return 0;
        }

        //先持续递归遍历到最左叶子节点
        int partLeft = dfs(node.left);
        if (partLeft < 0) {
            // 小于0的子树结果忽略
            partLeft = 0;
        }

        int partRight = dfs(node.right);
        if (partRight < 0) {
            // 小于0的子树结果忽略
            partRight = 0;
        }

        int part = node.val + partLeft + partRight;
        if (part > max) {
            max = part;
        }

        return node.val + Math.max(partLeft, partRight);/*返回左路径或右路径最大的值*/
    }

}
