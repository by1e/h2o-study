package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagArray;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：螺旋矩阵 II
 * <p>
 * 描述：给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
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
public class Algorithm00059 implements Algorithm<Object> {

    // Right
    private static final int R = 0;
    // Down
    private static final int D = 1;
    // Left
    private static final int L = 2;
    // Up
    private static final int U = 3;

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        // direction
        int dir = R;
        int num = 1;
        //两种做法：flag[]标记法；找出边界
        //这里使用边界法
        while (left <= right && top <= bottom) {
            switch (dir) {
                case R:
                    for (int i = left; i <= right; i++) {
                        matrix[top][i] = num++;
                    }
                    dir = D;
                    top++;
                    break;
                case D:
                    for (int i = top; i <= bottom; i++) {
                        matrix[i][right] = num++;
                    }
                    dir = L;
                    right--;
                    break;
                case L:
                    for (int i = right; i >= left; i--) {
                        matrix[bottom][i] = num++;
                    }
                    dir = U;
                    bottom--;
                    break;
                case U:
                    for (int i = bottom; i >= top; i--) {
                        matrix[i][left] = num++;
                    }
                    dir = R;
                    left++;
                    break;
                default:
                    break;
            }
        }
        return matrix;
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int n = (int) params[0];

        return generateMatrix(n);
    }

}
