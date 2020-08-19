package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagBinaryTree;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDeepFirstSearch;
import cn.by1e.h2o.study.interview.leetcode.algorithm.extend.TreeNode;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmSupport;

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

    @AlgorithmBody
    public int maxDepth(TreeNode root) {
        //递归法
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
