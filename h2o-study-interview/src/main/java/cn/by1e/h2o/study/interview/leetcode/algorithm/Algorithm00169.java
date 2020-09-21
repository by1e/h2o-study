package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.*;

/**
 * 标题：多数元素
 * <p>
 * 描述：给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * <p>
 * 标签：位运算、数组、分治算法
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagBitOperation
@TagArray
@TagDivideAndConquer
public class Algorithm00169 {

    /**
     * 已知解法：
     * 1、Hashet统计个数
     * 2、排序后返回Mid
     * 3、随机挑选数字判断Amount
     * 4、分治法细分统计
     * 5、Boyer-Moore投票算法
     */


    /**
     * 执行用时：2ms,在所有Java提交中击败了77.23%的用户
     * 内存消耗：42.2MB,在所有Java提交中击败了48.53%的用户
     */
//    @AlgorithmBody
    public int majorityElement(int[] nums) {
        //分治法
        int len = nums.length;
        int lft = 0;
        int rgt = len - 1;
        return majorityElement0(nums, len, lft, rgt);
    }

    private int majorityElement0(int[] nums, int len, int lft, int rgt) {
        if (lft == rgt) {
            return nums[lft];
        }

        int mid = (lft + rgt) / 2;
        int lftRes = majorityElement0(nums, len, lft, mid);
        int rgtRes = majorityElement0(nums, len, mid + 1, rgt);

        if (lftRes == rgtRes) {
            return lftRes;
        }

        int lftCount = countMajorityElement0(nums, lftRes, lft, rgt);
        int rgtCount = countMajorityElement0(nums, rgtRes, lft, rgt);

        return lftCount > rgtCount ? lftRes : rgtRes;
    }

    private int countMajorityElement0(int[] nums, int res, int lft, int rgt) {
        int count = 0;
        for (int i = lft; i <= rgt; i++) {
            if (nums[i] == res) {
                count++;
            }
        }
        return count;
    }

    @AlgorithmBody
    public int majorityElement2(int[] nums) {
        //Boyer-Moore投票算法
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

}
