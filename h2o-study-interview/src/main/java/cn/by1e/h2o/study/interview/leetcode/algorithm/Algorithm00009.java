package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.LevelMiddle;
import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.TagNumer;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;

/**
 * 标题：回文数
 * <p>
 * 描述：判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 标签：数字
 * <p>
 * 难度：中等
 *
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@LevelMiddle
@TagNumer
public class Algorithm00009 implements Algorithm<Boolean> {

    /**
     * 执行用时：9ms,在所有Java提交中击败了98.73%的用户
     * 内存消耗：38.9MB,在所有Java提交中击败了5.14%的用户
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        final int max = 10;
        final int[] chs = new int[max];
        int cur = max - 1;
        int t = x;
        while (true) {
            chs[cur--] = t % 10;
            if ((t /= 10) == 0) {
                break;
            }
        }
        for (int pos = cur + 1; pos < max; pos++) {
            if (chs[pos] != chs[max - pos + cur]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 执行用时：9ms,在所有Java提交中击败了98.73%的用户
     * 内存消耗：39.4MB,在所有Java提交中击败了5.14%的用户
     */
    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int t = x;
        int y = 0;
        while (t > 0) {
            y = y * 10 + t % 10;
            t /= 10;
        }
        return x == y;
    }

    @Override
    public AlgorithmFunction<Boolean> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private Boolean func(AlgorithmInput input) {
        Object[] params = input.getParams();

        int x = (int) params[0];

        //return isPalindrome(x);
        return isPalindrome2(x);
    }

}
