package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDynamicProgramming;

/**
 * 标题：买卖股票的最佳时机
 * <p>
 * 描述：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * 标签：数组，动态规划
 * <p>
 * 难度：简单
 * <p>
 * 补充：
 * LeetCode 121：最多进行 1 笔交易（k=1）【贪心】
 * LeetCode 122：不限交易次数（k=+inf）【二维 DP】
 * LeetCode 309：不限交易次数（k=+inf），但有「冷冻期」的额外条件
 * LeetCode 714：不限交易次数（k=+inf），但有「手续费」的额外条件
 * LeetCode 123：最多进行 2 笔交易（k=2）【三维 DP】
 * LeetCode 188：最多进行 k 次交易
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagArray
@TagDynamicProgramming
public class Algorithm00121 {

    /**
     * 执行用时：2ms,在所有Java提交中击败了61.82%的用户
     * 内存消耗：39.9MB,在所有Java提交中击败了25.01%的用户
     */
    @AlgorithmBody
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 1) {
            return 0;
        }
        int[] f = new int[len];
        f[0] = prices[0];
        int maxProfit = 0;
        for (int day = 1; day < len; day++) {
            // f(x)的含义是到第x天时之前的最低股价，可以理解为买的那天
            f[day] = Math.min(f[day - 1], prices[day]);
            // 当天卖的利润
            int profit = prices[day] - f[day];
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }

}
