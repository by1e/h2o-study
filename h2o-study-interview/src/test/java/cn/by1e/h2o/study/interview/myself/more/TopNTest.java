package cn.by1e.h2o.study.interview.myself.more;

import cn.by1e.h2o.study.interview.myself.rand.IntArrayRand;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.JsonUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * @author bangquan.qian
 * @date 2020-08-25 14:17
 */
public class TopNTest {

    @Test
    public void test1() {
        _test((e, arr) -> new TopN().topN(e, arr));
    }

    @Test
    public void test2() {
        _test((e, arr) -> new TopN0().findTopN(e, arr));
    }

    private void _test(BiFunction<Integer, int[], int[]> consumer) {
        int n = 10;
        int[] rand = IntArrayRand.rand(RandomUtils.nextInt(100, 100), 0, 100);
        int[] top = consumer.apply(n, rand);
        ConsoleUtils.jsons(top.length, top);
        Arrays.sort(top);
        ConsoleUtils.jsons(top.length, top);
        Arrays.sort(rand);
        int[] ret = Arrays.copyOfRange(rand, rand.length - n, rand.length);
        ConsoleUtils.jsons(ret.length, ret);
        ConsoleUtils.sout(JsonUtils.toJsonString(top).equals(JsonUtils.toJsonString(ret)));
    }

}
