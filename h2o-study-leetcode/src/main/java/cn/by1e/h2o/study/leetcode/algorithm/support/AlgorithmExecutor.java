package cn.by1e.h2o.study.leetcode.algorithm.support;

import cn.by1e.ox.core.exception.NotSupportException;
import cn.by1e.ox.core.util.InvokeUtils;
import org.apache.commons.lang3.time.StopWatch;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
public class AlgorithmExecutor {

    private AlgorithmExecutor() {
        throw new NotSupportException();
    }

    public static <T> Algorithm<T> of(Class<? extends Algorithm<T>> clz) {
        return InvokeUtils.invokeRe(clz::newInstance);
    }

    public static <T> AlgorithmOutput<T> execute(Algorithm<T> algorithm, Object... params) {
        return algorithm.execute(AlgorithmInput.of(params));
    }

    public static <T> AlgorithmOutput<T> execute(Class<? extends Algorithm<T>> clz, Object... params) {
        return execute(of(clz), params);
    }

    public static <T> AlgorithmOutput<T> function(AlgorithmFunction<T> function) {
        StopWatch watch = new StopWatch();
        T result = null;
        try {
            watch.start();
            result = function.func();
        } finally {
            watch.stop();
        }
        return AlgorithmOutput.<T>builder()
                .result(result)
                .report(watch.toString())
                .build();
    }

}
