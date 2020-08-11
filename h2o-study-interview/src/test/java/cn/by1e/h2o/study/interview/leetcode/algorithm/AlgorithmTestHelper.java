package cn.by1e.h2o.study.interview.leetcode.algorithm;

import cn.by1e.h2o.study.interview.leetcode.algorithm.support.Algorithm;
import cn.by1e.h2o.study.interview.leetcode.algorithm.support.AlgorithmExecutor;
import cn.by1e.ox.core.util.ConsoleUtils;

/**
 * @author bangquan.qian
 * @date 2020-07-21 18:32
 */
public class AlgorithmTestHelper {

    public static <T> void test(Class<? extends Algorithm<T>> clz, Object... params) {
        ConsoleUtils.prettyJson(AlgorithmExecutor.execute(clz, params));
    }

}
