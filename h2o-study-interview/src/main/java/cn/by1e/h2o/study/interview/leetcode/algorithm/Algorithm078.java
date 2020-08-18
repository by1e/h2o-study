package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.*;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：子集
 * <p>
 * 描述：给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * <p>
 * 标签：位运算、数组、回溯算法
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagArray
@TagBitOperation
@TagBackTrack
public class Algorithm078 implements Algorithm<Object> {

    /**
     * 执行用时：1ms,在所有Java提交中击败了99.25%的用户
     * 内存消耗：39.9MB,在所有Java提交中击败了81.03%的用户
     */
    public List<List<Integer>> subsets(int[] nums) {
        //回溯，更通用一点的做法
        List<List<Integer>> lst = new ArrayList<>();
        int len = nums.length;
        // box: 子集的期望长度
        for (int box = 0; box <= len; box++) {
            // 每个子集的存储
            List<Integer> sub = new ArrayList<>();
            subsets0(lst, nums, len, box, sub, 0);
        }
        return lst;
    }

    private void subsets0(final List<List<Integer>> lst, final int[] nums, final int len, final int box, final List<Integer> sub, final int lft) {
        if (sub.size() == box) {
            lst.add(new ArrayList<>(sub));
            return;
        }

        for (int idx = lft; idx < len; idx++) {
            sub.add(nums[idx]);
            subsets0(lst, nums, len, box, sub, idx + 1);
            sub.remove(sub.size() - 1);
        }
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int[] nums = (int[]) params[0];

        return subsets(nums);
    }

}
