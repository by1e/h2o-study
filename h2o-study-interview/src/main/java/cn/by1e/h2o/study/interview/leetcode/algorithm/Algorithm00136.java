package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.*;

/**
 * 标题：只出现一次的数字
 * <p>
 * 描述：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 标签：hash法，位运算
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagBitOperation
@TagHashTable
public class Algorithm00136 {

    /**
     * 1、暴力法，双重循环
     * 2、计数法/hash法
     * 3、先排序再遍历找
     * 4、位运算，异或
     */

    /**
     * 执行用时：1ms,在所有Java提交中击败了99.69%的用户
     * 内存消耗：41.1MB,在所有Java提交中击败了6.69%的用户
     */
    @AlgorithmBody
    public int singleNumber(int[] nums) {
        int len = nums.length;
        if (len < 1) {
            return 0;
        }
        int min = nums[0];
        int max = nums[0];
        for (int idx = 0; idx < len; idx++) {
            int num = nums[idx];
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        int tlen = max - min + 1;
        int[] tab = new int[tlen];
        for (int idx = 0; idx < len; idx++) {
            tab[nums[idx] - min]++;
        }
        for (int idx = 0; idx < tlen; idx++) {
            if (tab[idx] == 1) {
                return idx + min;
            }
        }
        return 0;
    }

    /**
     * 执行用时：1ms,在所有Java提交中击败了99.69%的用户
     * 内存消耗：40.7MB,在所有Java提交中击败了74.08%的用户
     */
    public int singleNumber2(int[] nums) {
        // 位运算，异或
        int len = nums.length;
        int num = 0;
        for (int idx = 0; idx < len; idx++) {
            num ^= nums[idx];
        }
        return num;
    }

}
