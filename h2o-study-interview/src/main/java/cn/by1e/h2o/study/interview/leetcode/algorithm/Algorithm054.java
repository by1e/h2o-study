package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 标题：螺旋矩阵
 * <p>
 * 描述：给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 标签：数组
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagArray
public class Algorithm054 implements Algorithm<Object> {

    // Right
    private static final int R = 0;
    // Down
    private static final int D = 1;
    // Left
    private static final int L = 2;
    // Up
    private static final int U = 3;

    /**
     * 执行用时：0ms,在所有Java提交中击败了100.00%的用户
     * 内存消耗：37.6MB,在所有Java提交中击败了47.72%的用户
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        if (n < 1) {
            return Collections.emptyList();
        }
        int m = matrix[0].length;
        if (m < 1) {
            return Collections.emptyList();
        }
        int left = 0;
        int right = m - 1;
        int top = 0;
        int bottom = n - 1;
        // direction
        int dir = R;
        int[] res = new int[m * n];
        int pos = 0;
        //两种做法：flag[]标记法；找出边界
        //这里使用边界法
        while (left <= right && top <= bottom) {
            switch (dir) {
                case R:
                    for (int i = left; i <= right; i++) {
                        res[pos++] = matrix[top][i];
                    }
                    dir = D;
                    top++;
                    break;
                case D:
                    for (int i = top; i <= bottom; i++) {
                        res[pos++] = matrix[i][right];
                    }
                    dir = L;
                    right--;
                    break;
                case L:
                    for (int i = right; i >= left; i--) {
                        res[pos++] = matrix[bottom][i];
                    }
                    dir = U;
                    bottom--;
                    break;
                case U:
                    for (int i = bottom; i >= top; i--) {
                        res[pos++] = matrix[i][left];
                    }
                    dir = R;
                    left++;
                    break;
                default:
                    break;
            }
        }
        List<Integer> lst = new ArrayList<>(res.length);
        for (int num : res) {
            lst.add(num);
        }
        return lst;
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int m = params.length;
        int[][] matrix = new int[m][];
        for (int idx = 0; idx < m; idx++) {
            matrix[idx] = (int[]) params[idx];
        }

        return spiralOrder(matrix);
    }

}
