package cn.by1e.h2o.study.interview.leetcode.algorithm.support;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.ox.core.util.InvokeUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;

/**
 * @author bangquan.qian
 * @date 2020-08-19 13:07
 */
public class AlgorithmSupport implements Algorithm<Object> {

    @Override
    public AlgorithmFunction<Object> execute(AlgorithmInput input) {
        return () -> func(input);
    }

    protected Object func(AlgorithmInput input) {
        Class<? extends AlgorithmSupport> clz = this.getClass();
        Method[] methods = clz.getDeclaredMethods();
        Method targetMethod = null;
        for (Method method : methods) {
            AlgorithmBody annotation = method.getAnnotation(AlgorithmBody.class);
            if (annotation != null) {
                targetMethod = method;
                break;
            }
        }
        if (targetMethod == null) {
            throw new RuntimeException("not found @AlgorithmBody");
        }
        targetMethod.setAccessible(true);
        final Method algorithmMethod = targetMethod;
        return InvokeUtils.invokeRe(() -> {
            Object[] params = input.getParams();
            if (ArrayUtils.isEmpty(params)) {
                return algorithmMethod.invoke(this);
            }
            return algorithmMethod.invoke(this, params);
        });
    }
}
