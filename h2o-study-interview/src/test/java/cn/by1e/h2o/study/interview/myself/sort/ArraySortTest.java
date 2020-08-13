package cn.by1e.h2o.study.interview.myself.sort;

import cn.by1e.h2o.study.interview.myself.rand.IntArrayRand;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author bangquan.qian
 * @date 2020-08-12 14:31
 */
public class ArraySortTest {

    @Test
    public void test1() {
        testA(ArraySort::bubble);
        testB(ArraySort::bubble);
    }

    @Test
    public void test2() {
        testA(ArraySort::cocktail);
    }

    @Test
    public void test3() {
        testA(ArraySort::select);
        testB(ArraySort::select);
    }

    @Test
    public void test4() {
        testA(ArraySort::insert);
        testB(ArraySort::insert);
    }

    @Test
    public void test5() {
        testA(ArraySort::insert2);
    }

    @Test
    public void test6() {
        testA(ArraySort::shell);
    }

    private void testB(BiConsumer<Integer[], Integer> consumer) {
        int gap = RandomUtils.nextInt(2, 5);
        ConsoleUtils.sout("gap=" + gap);

        Integer[] arr = new Integer[0];
        consumer.accept(arr, gap);
        ConsoleUtils.json(arr);

        arr = new Integer[]{1};
        consumer.accept(arr, gap);
        ConsoleUtils.json(arr);

        arr = rand();
        ConsoleUtils.json(arr);
        consumer.accept(arr, gap);
        ConsoleUtils.json(arr);
        ConsoleUtils.sout(checkSorted(arr, gap));
    }

    private void testA(Consumer<Integer[]> consumer) {
        Integer[] arr = new Integer[0];
        consumer.accept(arr);
        ConsoleUtils.json(arr);

        arr = new Integer[]{1};
        consumer.accept(arr);
        ConsoleUtils.json(arr);

        arr = rand();
        ConsoleUtils.json(arr);
        consumer.accept(arr);
        ConsoleUtils.json(arr);
        ConsoleUtils.sout(checkSorted(arr));
    }

    private boolean checkSorted(Integer[] arr) {
        return checkSorted(arr, 1);
    }

    private boolean checkSorted(Integer[] arr, int gap) {
        int len = arr.length;
        for (int idx = gap; idx < len; idx += gap) {
            if (arr[idx - gap] > arr[idx]) {
                return false;
            }
        }
        return true;
    }

    private Integer[] rand() {
        return ArrayUtils.toObject(IntArrayRand.rand2(RandomUtils.nextInt(50, 100), 0, 99));
    }

}
