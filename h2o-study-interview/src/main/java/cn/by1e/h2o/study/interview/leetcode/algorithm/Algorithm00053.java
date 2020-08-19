package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDivideAndConquer;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDynamicProgramming;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：最大子序和
 * <p>
 * 描述：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 标签：数组、分治、动态规划
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagArray
@TagDivideAndConquer
@TagDynamicProgramming
public class Algorithm00053 implements Algorithm<Object> {

    // 超时
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int len = nums.length;
        // 暴力
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    /**
     * 执行用时：1ms,在所有Java提交中击败了95.88%的用户
     * 内存消耗：39.6MB,在所有Java提交中击败了77.69%的用户
     */
    public int maxSubArray2(int[] nums) {
        int pre = nums[0];
        int max = pre;
        int len = nums.length;
        // 动态规划
        for (int i = 1; i < len; i++) {
            // f(x)的含义是以x结尾的数组已知最大解，x的范围是[0,len-1]
            // f(x) = max{ f(x-1) + nums[x], nums[x] }
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(pre, max);
        }
        return max;
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int[] nums = (int[]) params[0];

//        return maxSubArray(nums);
        return maxSubArray2(nums);
    }

}
