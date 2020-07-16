package cn.by1e.h2o.study.leetcode.algorithm.support;

import cn.by1e.ox.core.exception.NotSupportException;
import lombok.Getter;

/**
 * @author bangquan.qian
 * @date 2020-07-16 12:39
 */
public class AlgorithmInput {

    @Getter
    private Object[] params;

    private AlgorithmInput() {
        throw new NotSupportException();
    }

    private AlgorithmInput(Object[] params) {
        this.params = params;
    }

    public static AlgorithmInput of(Object... params) {
        return new AlgorithmInput(params);
    }

}
