package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagGreedy;

/**
 * 标题：买卖股票的最佳时机 II
 * <p>
 * 描述：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 标签：贪心算法、数组
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagArray
@TagGreedy
public class Algorithm00122 {

    // 最大利润
    private int maxProfit0 = 0;

    private static final int HOLD_CASH = 0;
    private static final int HOLD_STOCK = 1;

    // 超时
    @AlgorithmBody
    public int maxProfit(int[] prices) {
        /**
         * 暴搜法：
         * 如果持有现金，那么接下来有两个选择：继续持有现金，或买入股票
         * 如果持有股票，那么接下来有两个选择：继续持有股票，或者卖出
         * 每一天可以进行操作一次，那么可以把天当做树的层级
         * 树的节点有两种情况：<b>现金，持股</b>
         * 树的枝干表示的含义：不操作，买入/卖出
         * 实现dfs递归回溯得出最大值
         *
         *        现金
         * (空仓) /   \(买入)
         *      现金 股票
         *
         *        股票
         *  (卖出)/   \(持有)
         *      现金 股票
         *
         */
        // 总天数
        int len = prices.length;
        if (len < 1) {
            return 0;
        }
        // 0: 持有现金 1: 持有股票
        int status = HOLD_CASH;
        // 第0天
        int day = 0;
        int profit = maxProfit0;
        // dfs
        maxProfitDfs(prices, len, day, status, profit);
        return maxProfit0;
    }

    private void maxProfitDfs(int[] prices, int len, int day, int status, int profit) {
        if (day == len) {
            this.maxProfit0 = Math.max(this.maxProfit0, profit);
            return;
        }

        //先递归到最底层的叶子节点，而后回溯逐步走到其他叶子节点
        //初始status==HOLD_CASH，一路走到最底层的叶子节点
        maxProfitDfs(prices, len, day + 1, status, profit);

        //回溯的节点
        if (status == HOLD_CASH) {
            //此时走向右节点，即"买入"操作后节点状态为"持有股票"
            maxProfitDfs(prices, len, day + 1, HOLD_STOCK, profit - prices[day]);
        } else {
            // 此时状态为持有股票
            //此时走向左节点，即"买出"操作后节点状态为"持有现金"
            maxProfitDfs(prices, len, day + 1, HOLD_CASH, profit + prices[day]);
        }
    }

    /**
     * 执行用时：4ms,在所有Java提交中击败了14.00%的用户
     * 内存消耗：39.6MB,在所有Java提交中击败了69.75%的用户
     */
    @AlgorithmBody
    public int maxProfit2(int[] prices) {
        /**
         * 二维DP：也可以降成两个一维DP
         *  将暴搜的思想转换成动态规划
         */
        int len = prices.length;
        if (len < 1) {
            return 0;
        }

        //f(day,status)=profit
        int[][] f = new int[len][2];

        //初始状态，第0天的利润
        f[0][HOLD_CASH] = 0;
        f[0][HOLD_STOCK] = -prices[0];

        for (int day = 1; day < len; day++) {
            //f(x)代表的含义为第day天的最大利润，但此时有两种状态：现金，持股
            f[day][HOLD_CASH] = Math.max(f[day - 1][HOLD_STOCK] + prices[day]/*卖出股票*/, f[day - 1][HOLD_CASH]/*继续持有现金，即不操作*/);
            f[day][HOLD_STOCK] = Math.max(f[day - 1][HOLD_STOCK]/*继续持有股票，即不操作*/, f[day - 1][HOLD_CASH] - prices[day]/*买入股票*/);
        }

        //返回最后一天且状态为持有现金时的利润
        return f[len - 1][HOLD_CASH];
    }

    /**
     * 执行用时：1ms,在所有Java提交中击败了99.50%的用户
     * 内存消耗：39.9MB,在所有Java提交中击败了30.59%的用户
     */
    //    @AlgorithmBody
    public int maxProfit3(int[] prices) {
        /**
         * 贪心：
         */
        int len = prices.length;
        int profit = 0;
        for (int day = 1; day < len; day++) {
            int diff = prices[day] - prices[day - 1];
            if (diff > 0) {
                //每次能挣钱的股价差价我"都要"，最终能实现利润最大化
                profit += diff;
            }
        }
        return profit;
    }

}
