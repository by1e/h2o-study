package cn.by1e.h2o.study.interview.leetcode.algorithm.support;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author bangquan.qian
 * @date 2020-07-16 12:44
 */
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmOutput<T> {

    private T result;

    private String report;

}
