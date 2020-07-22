package cn.by1e.h2o.study.leetcode.algorithm;

import cn.by1e.h2o.study.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：最接近的三数之和
 * <p>
 * 描述：给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 标签：数组，双指针
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
public class Algorithm016 implements Algorithm<Integer> {

    /**
     * 执行用时：163ms,在所有Java提交中击败了6.12%的用户
     * 内存消耗：39.6MB,在所有Java提交中击败了6.82%的用户
     */
    public int threeSumClosest(int[] nums, int target) {
        int sum = 0;
        int dst = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int s = nums[i] + nums[j] + nums[k];
                    int x = Math.abs(s - target);
                    if (dst > x || dst == -1) {
                        dst = x;
                        sum = s;
                    }
                }
            }
        }
        return sum;
    }

    public int threeSumClosest2(int[] nums, int target) {
        return 0;
    }

    @Override
    public AlgorithmFunction<Integer> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Integer func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int[] nums = (int[]) params[0];
        int target = (int) params[1];

        //return threeSumClosest(nums, target);
        return threeSumClosest2(nums, target);
    }

}
