package cn.by1e.h2o.study.leetcode.algorithm;

import cn.by1e.h2o.study.leetcode.algorithm.support.AlgorithmExecutor;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-16 11:35
 */
public class Algorithm000Test {

    @Test
    public void test() {
        Object[] params = new Object[]{1};

        ConsoleUtils.prettyJson(AlgorithmExecutor.execute(Algorithm000.class, params));
    }

}
