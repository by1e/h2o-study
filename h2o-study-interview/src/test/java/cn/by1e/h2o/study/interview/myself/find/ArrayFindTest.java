package cn.by1e.h2o.study.interview.myself.find;

import cn.by1e.h2o.study.interview.myself.rand.IntArrayRand;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-08-12 10:51
 */
public class ArrayFindTest {

    @Test
    public void test1() {
        int[] rand = IntArrayRand.rand(10, 0, 9);
        ConsoleUtils.json(rand);
        Integer min = ArrayFind.min(ArrayUtils.toObject(rand));
        ConsoleUtils.sout(min);
    }

}
