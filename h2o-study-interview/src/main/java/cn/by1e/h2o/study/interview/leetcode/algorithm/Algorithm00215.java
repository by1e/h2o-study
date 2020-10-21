package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.*;

import java.util.Arrays;

/**
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@Title("数组中的第K个最大元素")
@Content("在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。")
@LevelMiddle
@TagDivideAndConquer
@TagHeap
public class Algorithm00215 {

    /**
     * 执行用时：2ms,在所有Java提交中击败了90.69%的用户
     * 内存消耗：38.5MB,在所有Java提交中击败了99.06%的用户
     */
    @AlgorithmBody
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}
