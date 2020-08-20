package cn.by1e.h2o.study.interview.leetcode.algorithm;

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

    public int maxProfit(int[] prices) {
        /**
         * 暴力法：
         * 如果持有现金，那么接下来有两个选择：继续持有现金，或买入股票
         * 如果持有股票，那么接下来有两个选择：继续持有股票，或者卖出
         * 每一天可以进行操作一次，那么可以把天当做树的层级，树的节点有两种情况：现金，持股
         * 实现dfs递归回溯得出最大值
         */
        return null;
    }

}
