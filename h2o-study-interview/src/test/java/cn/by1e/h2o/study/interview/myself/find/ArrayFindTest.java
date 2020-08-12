package cn.by1e.h2o.study.interview.myself.find;

import cn.by1e.h2o.study.interview.myself.rand.IntArrayRand;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author bangquan.qian
 * @date 2020-08-12 10:51
 */
public class ArrayFindTest {

    @Test
    public void test1() {
        Integer[] rand = rand();
        ConsoleUtils.json(rand);
        Integer min = ArrayFind.min(rand);
        ConsoleUtils.sout(min);
    }

    @Test
    public void test2() {
        Integer[] rand = rand();
        ConsoleUtils.json(rand);
        Integer max = ArrayFind.max(rand);
        ConsoleUtils.sout(max);
    }

    @Test
    public void test3() {
        Integer[] rand = rand();
        ConsoleUtils.json(rand);
        int idx1 = ArrayFind.line(rand, rand[rand.length - 1]);
        ConsoleUtils.jsons(idx1, rand.length - 1, rand[rand.length - 1], rand[idx1]);
        Integer idx2 = ArrayFind.line(rand, 100);
        ConsoleUtils.sout(idx2);
    }

    @Test
    public void test4() {
        Integer[] rand = rand();
        Arrays.sort(rand);
        ConsoleUtils.json(rand);
        int idx1 = ArrayFind.half(rand, rand[rand.length - 1]);
        ConsoleUtils.jsons(idx1, rand.length - 1, rand[rand.length - 1], rand[idx1]);
        Integer idx2 = ArrayFind.line(rand, 100);
        ConsoleUtils.sout(idx2);
    }

    @Test
    public void test5() {
        Integer[] rand = rand();
        ConsoleUtils.json(rand);
        int idx1 = ArrayFind.hash(rand, rand[rand.length - 1]);
        ConsoleUtils.jsons(idx1, rand.length - 1, rand[rand.length - 1], rand[idx1]);
        Integer idx2 = ArrayFind.line(rand, 100);
        ConsoleUtils.sout(idx2);
    }

    private Integer[] rand() {
        return ArrayUtils.toObject(IntArrayRand.rand(50, 0, 99));
    }

}
