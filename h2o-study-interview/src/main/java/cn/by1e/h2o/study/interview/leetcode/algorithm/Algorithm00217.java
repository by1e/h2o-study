package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@Title("存在重复元素")
@Content(
        "给定一个整数数组，判断是否存在重复元素。 " +
                "如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。"
)
@LevelSimple
@TagHashTable
@TagArray
public class Algorithm00217 {

    /**
     * 执行用时：8ms,在所有Java提交中击败了56.93%的用户
     * 内存消耗：44.6MB,在所有Java提交中击败了56.31%的用户
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

}
