package cn.by1e.h2o.study.interview.leetcode.algorithm.support;

import cn.by1e.ox.core.constant.Constants;
import cn.by1e.ox.core.util.AssertUtils;
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

    @SuppressWarnings(Constants.UNCHECKED)
    public static <T> void smart(Object... params) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        Class<? extends StackTraceElement> elementClass = element.getClass();
        TestAlgorithm testAlgorithmAnnotation = elementClass.getAnnotation(TestAlgorithm.class);
        Class<? extends Algorithm<T>> clz = null;
        if (testAlgorithmAnnotation != null) {
            clz = (Class<? extends Algorithm<T>>) testAlgorithmAnnotation.value();
        } else {
            String canonicalName = element.getClassName();
            String algorithmName = canonicalName.substring(0, canonicalName.length() - 4);
            clz = (Class<? extends Algorithm<T>>) InvokeUtils.invokeRe(() -> ClassUtils.getClass(algorithmName));
        }
        AssertUtils.notNull(clz);
        test(clz, params);
    }

}
