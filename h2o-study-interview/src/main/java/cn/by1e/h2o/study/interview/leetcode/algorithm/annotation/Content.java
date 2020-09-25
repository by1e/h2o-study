package cn.by1e.h2o.study.interview.leetcode.algorithm.annotation;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bangquan.qian
 * @date 2020-09-23 13:47
 */

public @interface Content {

    String value() default StringUtils.EMPTY;
}
