package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelSimple;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagDoublePointer;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：合并两个有序数组
 * <p>
 * 描述：给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p>
 * 标签：数组，双指针
 * <p>
 * 难度：简单
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelSimple
@TagArray
@TagDoublePointer
public class Algorithm088 implements Algorithm<Object> {

    /**
     * 执行用时：1ms,在所有Java提交中击败了23.14%的用户
     * 内存消耗：39.9MB,在所有Java提交中击败了51.50%的用户
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //类似插入排序
        int len1 = nums1.length;
        int p1 = 0;
        for (int p2 = 0; p2 < n; p2++) {
            int v = nums2[p2];
            int j = p1;
            //找出插入点
            for (; j < m; j++) {
                if (v < nums1[j]) {
                    break;
                }
            }
            //移动位置
            for (int k = len1 - 1; k > j; k--) {
                nums1[k] = nums1[k - 1];
            }
            //安置
            nums1[j] = v;
            p1 = j + 1;
            m++;
        }
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int[] nums1 = (int[]) params[0];
        int m = (int) params[1];
        int[] nums2 = (int[]) params[2];
        int n = (int) params[3];

        merge(nums1, m, nums2, n);
        return nums1;
    }

}
