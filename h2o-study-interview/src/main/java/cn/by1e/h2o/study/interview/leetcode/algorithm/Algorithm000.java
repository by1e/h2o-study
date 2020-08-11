package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmFunction;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmInput;
import cn.by1e.h2o.study.leetcode.algorithm.support.*;
import cn.by1e.ox.core.util.SleepUtils;

/**
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
public class Algorithm000 implements Algorithm<Integer> {

    public int example(int a) {
        SleepUtils.sleepSeconds(1);
        return a + 1;
    }

    @Override
    public AlgorithmFunction<Integer> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    private int func(AlgorithmInput input) {
        return example((int) input.getParams()[0]);
    }

}
