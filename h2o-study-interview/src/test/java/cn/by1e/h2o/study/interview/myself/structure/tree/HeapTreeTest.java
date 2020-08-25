package cn.by1e.h2o.study.interview.myself.structure.tree;

import cn.by1e.h2o.study.interview.myself.rand.IntArrayRand;
import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author bangquan.qian
 * @date 2020-08-25 10:50
 */
public class HeapTreeTest {

    @Test
    public void test1() {
        _test(MinHeapTree.class);
    }

    @Test
    public void test2() {
        _test(MaxHeapTree.class);
    }

    private void _test(Class<? extends HeapTree> treeClass) {
        int[] rand0 = IntArrayRand.rand(RandomUtils.nextInt(5, 20), 0, 19);
        ConsoleUtils.json(rand0);
        HeapTree tree = null;
        try {
            Constructor<? extends HeapTree> constructor = treeClass.getConstructor(int[].class);
            tree = constructor.newInstance(rand0);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        int[] key = tree.getKey();
        ConsoleUtils.json(key);
        ConsoleUtils.json(key.length == rand0.length);
        int[] rand = IntArrayRand.rand(20, 0, 29);
        ConsoleUtils.json(rand);
        for (int num : rand) {
            tree.put(num, num);
        }
        key = tree.getKey();
        ConsoleUtils.json(key);
        ConsoleUtils.json(key.length == rand.length + rand0.length);
    }
}
