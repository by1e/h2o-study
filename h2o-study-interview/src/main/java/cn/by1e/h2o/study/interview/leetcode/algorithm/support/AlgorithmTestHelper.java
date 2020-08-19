package cn.by1e.h2o.study.interview.leetcode.algorithm.support;

import cn.by1e.ox.core.constant.Constants;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;
import org.apache.commons.lang3.ClassUtils;

/**
 * @author bangquan.qian
 * @date 2020-07-21 18:32
 */
public class AlgorithmTestHelper {

    public static <T> void test(Class<? extends Algorithm<T>> clz, Object... params) {
        ConsoleUtils.prettyJson(AlgorithmExecutor.execute(clz, params));
    }

    public static <T> void smart(Object... params) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        String canonicalName = element.getClassName();
        String algorithmName = canonicalName.substring(0, canonicalName.length() - 4);
        @SuppressWarnings(Constants.UNCHECKED)
        Class<? extends Algorithm<T>> clz = (Class<? extends Algorithm<T>>) InvokeUtils.invokeRe(() -> ClassUtils.getClass(algorithmName));
        test(clz, params);
    }

}
