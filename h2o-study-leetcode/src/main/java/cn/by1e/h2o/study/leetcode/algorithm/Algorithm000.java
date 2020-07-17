package cn.by1e.h2o.study.leetcode.algorithm;

import cn.by1e.h2o.study.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmExecutor;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmInput;
import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmOutput;
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
    public AlgorithmOutput<Integer> execute(AlgorithmInput input) {
        return AlgorithmExecutor.function(() -> example((int) input.getParams()[0]));
    }

}
