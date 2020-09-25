package cn.by1e.h2o.study.interview.leetcode.algorithm.support;

import cn.by1e.h2o.study.interview.leetcode.algorithm.Algorithm00000;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author bangquan.qian
 * @date 2020-09-25 14:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TestAlgorithm {

    Class<?> value() default Algorithm00000.class;

}
