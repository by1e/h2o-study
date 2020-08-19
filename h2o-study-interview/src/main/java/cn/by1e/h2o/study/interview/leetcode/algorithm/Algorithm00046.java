package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagBackTrack;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：全排列
 * <p>
 * 描述：给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 标签：回溯
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagBackTrack
public class Algorithm00046 implements Algorithm<Object> {

    /**
     * 执行用时：1ms,在所有Java提交中击败了99.74%的用户
     * 内存消耗：40.3MB,在所有Java提交中击败了7.32%的用户
     */
    public List<List<Integer>> permute(int[] nums) {
        // 递归法
        List<List<Integer>> lst = new ArrayList<>();
        int len = nums.length;
        int[] arr = new int[len];
        for (int jdx = 0; jdx < len; jdx++) {
            dfs(nums, 0, len, arr, jdx, lst);
        }
        return lst;
    }

    private void dfs(final int[] nums, final int idx, final int len, final int[] arr, final int jdx, final List<List<Integer>> lst) {
        /*if (idx == len) {
            return;
        }*/

        arr[idx] = nums[jdx];

        for (int kdx = 0; kdx < len; kdx++) {
            int val = nums[kdx];
            boolean has = false;
            for (int cdx = 0; cdx <= idx; cdx++) {
                if (arr[cdx] == val) {
                    has = true;
                    break;
                }
            }
            if (has || idx + 1 > len) {
                continue;
            }
            dfs(nums, idx + 1, len, arr, kdx, lst);
        }

        if (idx == len - 1) {
            List<Integer> tgt = new ArrayList<>(len);
            for (int a : arr) {
                tgt.add(a);
            }
            lst.add(tgt);
        }

    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int[] nums = (int[]) params[0];

        return permute(nums);
    }

}
