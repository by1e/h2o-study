package cn.by1e.h2o.study.leetcode.algorithm.support;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author bangquan.qian
 * @date 2020-07-16 16:37
 */
@FunctionalInterface
public interface AlgorithmFunction<T> {

    T func(AlgorithmInput input, StopWatch watch);

}
