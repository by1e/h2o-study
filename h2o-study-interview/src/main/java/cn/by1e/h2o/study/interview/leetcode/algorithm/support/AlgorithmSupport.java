package cn.by1e.h2o.study.interview.leetcode.algorithm.support;

import cn.by1e.h2o.study.interview.leetcode.algorithm.annotation.AlgorithmBody;
import cn.by1e.ox.core.constant.Constants;
import cn.by1e.ox.core.util.InvokeUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;

/**
 * @author bangquan.qian
 * @date 2020-08-19 13:07
 */
public class AlgorithmSupport<T> implements Algorithm<T> {

    @Override
    @SuppressWarnings(Constants.UNCHECKED)
    public AlgorithmFunction<T> execute(AlgorithmInput input) {
        return () -> (T) func(input);
    }

    private Object algorithm;

    public AlgorithmSupport() {
        this.algorithm = this;
    }

    public AlgorithmSupport(Object algorithm) {
        this.algorithm = algorithm;
    }

    protected Object func(AlgorithmInput input) {
        Object target = algorithm;
        Class clz = target.getClass();
        Method[] methods = clz.getDeclaredMethods();
        Method targetMethod = null;
        if (methods.length == 1) {
            targetMethod = methods[0];
        } else {
            for (Method method : methods) {
                AlgorithmBody annotation = method.getAnnotation(AlgorithmBody.class);
                if (annotation != null) {
                    targetMethod = method;
                    break;
                }
            }
        }
        if (targetMethod == null) {
            //使用传统做法
            return oldFunc(input);
        }
        targetMethod.setAccessible(true);
        final Method algorithmMethod = targetMethod;
        return InvokeUtils.invokeRe(() -> {
            Object[] params = input.getParams();
            if (ArrayUtils.isEmpty(params)) {
                return algorithmMethod.invoke(target);
            }
            return algorithmMethod.invoke(target, params);
        });
    }

    private Object oldFunc(AlgorithmInput input) {
        if (!(algorithm instanceof Algorithm)) {
            throw new RuntimeException("unable to run, else need @AlgorithmBody");
        }
        return ((Algorithm) algorithm).execute(input).func();
    }
}
