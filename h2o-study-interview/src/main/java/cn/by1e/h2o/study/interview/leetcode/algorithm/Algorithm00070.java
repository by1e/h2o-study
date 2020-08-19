package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDynamicProgramming;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：爬楼梯
 * <p>
 * 描述：假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 标签：数组，动态规划
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagArray
@TagDynamicProgramming
public class Algorithm00070 implements Algorithm<Object> {

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：36.2MB,在所有Java提交中击败了84.97%的用户
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        //动态规划
        int len = n + 1;
        int[] f = new int[len];
        //楼梯从下标1开始
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i < len; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    //超时
    public int climbStairs2(int n) {
        //递归法
        if (n < 3) {
            return n;
        }
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int n = (int) params[0];

        return climbStairs(n);
    }

}
