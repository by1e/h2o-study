package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagHalfSearch;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：搜索旋转排序数组
 * <p>
 * 描述：假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 标签：数组，二分查找
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagArray
@TagHalfSearch
public class Algorithm00033 implements Algorithm<Integer> {

    /**
     * 执行用时：1ms,在所有Java提交中击败了17.55%的用户
     * 内存消耗：39.4MB,在所有Java提交中击败了17.74%的用户
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len < 1) {
            return -1;
        }
        // 先找出扭转点
        int cut = len;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                cut = i + 1;
            }
        }
        // 二分查找
        int pos = half(nums, target, 0, cut - 1);
        if (pos != -1) {
            return pos;
        }
        return half(nums, target, cut, len - 1);
    }

    private int half(int[] nums, int target, int lft, int rgt) {
        do {
            int mid = (lft + rgt) / 2;
            if (nums[mid] > target) {
                rgt = mid - 1;
            } else if (nums[mid] < target) {
                lft = mid + 1;
            } else {
                return mid;
            }
        } while (lft <= rgt);
        return -1;
    }

    @Override
    public AlgorithmFunction<Integer> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Integer func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int[] nums = (int[]) params[0];
        int target = (int) params[1];

        //return search(nums, target);
        return search(nums, target);
    }

}
