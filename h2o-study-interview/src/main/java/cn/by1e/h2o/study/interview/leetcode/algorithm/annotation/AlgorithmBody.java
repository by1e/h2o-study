package cn.by1e.h2o.study.interview.leetcode.algorithm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author bangquan.qian
 * @date 2020-08-19 13:41
 */
@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AlgorithmBody {

}
