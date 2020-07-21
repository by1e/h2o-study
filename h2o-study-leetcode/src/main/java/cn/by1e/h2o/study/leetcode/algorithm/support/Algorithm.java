package cn.by1e.h2o.study.leetcode.algorithm.support;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:38
 */
public interface Algorithm<T> {

    AlgorithmFunction<T> execute(AlgorithmInput input);

}
