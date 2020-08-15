package cn.by1e.h2o.study.interview.myself.sort;

import cn.by1e.h2o.study.interview.myself.rand.IntArrayRand;
import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author bangquan.qian
 * @date 2020-08-15 18:42
 */
public class ArraySortAgainTest {

    @Test
    public void test() {
        test0(ArraySortAgain::bubble);
        test0(ArraySortAgain::select);
        test0(ArraySortAgain::insert);
        test0(ArraySortAgain::quick);
        test0(ArraySortAgain::merge);
        test0(ArraySortAgain::merge2);
    }

    private void test0(Consumer<int[]> consumer) {
        int[] t = rand();
        ConsoleUtils.json(t);

        int[] s = Arrays.copyOf(t, t.length);
        Arrays.sort(s);

        consumer.accept(t);
        ConsoleUtils.json(t);

        ConsoleUtils.sout(check(s, t));
    }

    private boolean check(int[] s, int[] t) {
        return ObjectUtils.equals(JsonUtils.toJsonString(s), JsonUtils.toJsonString(t));
    }

    private int[] rand() {
        return IntArrayRand.rand2(RandomUtils.nextInt(10, 50), 0, 20);
    }

}
