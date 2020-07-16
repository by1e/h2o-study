package cn.by1e.h2o.study.leetcode.algorithm.support;

import cn.by1e.ox.core.exception.NotSupportException;
import cn.by1e.ox.core.internal.Invoker;
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

    public static <T> AlgorithmOutput<T> execute(Class<? extends Algorithm<T>> clz, Object... params) {
        return of(clz).execute(AlgorithmInput.of(params));
    }

    public static <T> AlgorithmOutput<T> execute(Algorithm<T> algorithm, Object... params) {
        return algorithm.execute(AlgorithmInput.of(params));
    }

    public static <T> AlgorithmOutput<T> function(AlgorithmFunction<T> function, AlgorithmInput input) {
        StopWatch watch = new StopWatch();
        return AlgorithmOutput.<T>builder()
                .result(function.func(input, watch))
                .report(watch.toString())
                .build();
    }

    public static <T> T watch(Invoker<T> invoker, StopWatch watch) {
        try {
            watch.start();
            return InvokeUtils.invokeRe(invoker);
        } finally {
            watch.stop();
        }
    }

}
