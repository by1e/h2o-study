package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagBackTrack;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：格雷编码
 * <p>
 * 描述：格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 格雷编码序列必须以 0 开头。
 * <p>
 * 标签：回溯
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagBackTrack
public class Algorithm00089 implements Algorithm<Object> {

    /**
     * 执行用时：1ms,在所有Java提交中击败了80.01%的用户
     * 内存消耗：37.8MB,在所有Java提交中击败了12.48%的用户
     */
    public List<Integer> grayCode(int n) {
        /**
         * 解释：
         * n=0,lst0 = [0]
         * n=1,lst1 = [0,1]
         * n=2,lst2 = [00,01,11,10] = [0,1,11,10] = [lst1,倒序遍历lst1最高位加1]
         * n=3,lst3 = [000,001,011,110,100,101,111,110] = [0,1,11,10,100,101,111,110] = [lst2,倒序遍历lst2最高位加1]
         */
        List<Integer> gray = new ArrayList<>();
        // 添加 0 这个初始结果
        gray.add(0);
        for (int i = 0; i < n; i++) {
            //倒序遍历，并且加上一个值添加到结果中
            for (int j = gray.size() - 1; j >= 0; j--) {
                gray.add(gray.get(j) | 1 << i);
            }
        }
        return gray;
    }

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Object func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int n = (int) params[0];

        return grayCode(n);
    }

}
