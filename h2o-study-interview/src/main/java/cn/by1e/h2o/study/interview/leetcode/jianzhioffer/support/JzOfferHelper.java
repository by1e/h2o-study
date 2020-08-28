package cn.by1e.h2o.study.interview.leetcode.jianzhioffer.support;

import cn.by1e.ox.core.constant.Constants;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;

/**
 * @author bangquan.qian
 * @date 2020-08-28 14:03
 */
public class JzOfferHelper {

    @SuppressWarnings(Constants.UNCHECKED)
    public static <T> T run(Class<?> clz, Object... args) {
        Method[] methods = clz.getDeclaredMethods();
        if (ArrayUtils.isEmpty(methods)) {
            return null;
        }
        Method method = methods[0];
        method.setAccessible(true);
        Object result = null;
        try {
            result = method.invoke(clz.newInstance(), args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return (T) result;
    }

}
